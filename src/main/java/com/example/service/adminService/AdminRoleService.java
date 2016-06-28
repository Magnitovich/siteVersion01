package com.example.service.adminService;

import com.example.controller.CarsController;
import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepositiry;
import com.example.exception.EmailCompareWithDBException;
import com.example.exception.NickNameCompareWithDBException;
import com.example.exception.UserHasMoreThatOneRoleException;
import com.example.model.RoleDto;
import com.example.model.UserAdminRightsDTO;
import com.example.model.UserRole;
import com.example.model.UsersModel;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AdminRoleService {

    @Autowired
    private UserRepository userRepositiry;

    @Autowired
    private UserRoleRepositiry userRoleRepositiry;


    private UserAdminRightsDTO getUserFromList(List<UserAdminRightsDTO> user, String name) {

        for(UserAdminRightsDTO u: user) {
            if (u.getName().equals(name)) {
                return u;
            }

        }
        return null;
    }

    public List<UserAdminRightsDTO> getAdminRights() {
        List<UserAdminRightsDTO> users = new ArrayList<>();
        ArrayList<UserRole> availableUserRoles = Lists.newArrayList(userRoleRepositiry.findAll());

        //для каждого пользователя из userRepositiry.findAll() выполняется forEach
        //создаем переменную, в данном случае user имя не принципиально и в эту переменную передаем функцию
        //user наполняем данными для каждого пользователя с userRepositiry.findAll()
        userRepositiry.findAll().forEach(user -> {
            //этим уч кода мы набираем User(ов)
            UserAdminRightsDTO dto = new UserAdminRightsDTO();
            dto.setName(user.getName());

            //этим уч мы набираем роли для каждого конкретного User(а) из БД
            List<String> userRoles = new ArrayList<>();
            user.getUserRoles().forEach(role -> {
                userRoles.add(role.getRole());
            });
            dto.setRole(userRoles);

            //этот участок кода сверяет какие роли относятся к User(y) на выходе мы получаем
            // список ролей с true или false

            List<RoleDto> userRoleDtos = new ArrayList<>();

//          ArrayList<UserRole> availableUserRoles = Lists.newArrayList(userRoleRepositiry.findAll());
            availableUserRoles.forEach(userRoleEntity -> {
                RoleDto roleDto = new RoleDto();
                String roleNameInDB = userRoleEntity.getRole();

                roleDto.setNameRole(roleNameInDB);
                roleDto.setApplied(userRoles.contains(roleNameInDB));

                //тут для каждого пользователя выдаестя ответ по существующим ролям true или false
                userRoleDtos.add(roleDto);
            });

            dto.setRoleDtos(userRoleDtos);
            //получаем ответ у какого пользователя какие роли в БД
            users.add(dto);
        });

        return users;
    }

    public void addRightsAdmin(List<String> roles) {
        List<UserAdminRightsDTO> user = new ArrayList<>();

        for (String role:roles) {

            String[] split = role.split("_");
            String name = split[0];
            //этот метод находит пользователя из списка

            UserAdminRightsDTO userFromList = getUserFromList(user, name);
            if (userFromList == null) {
                //это происходит если Имя и Роль одни
                UserAdminRightsDTO userAdminRightsDTO = new UserAdminRightsDTO();
                userAdminRightsDTO.setName(name);
                userAdminRightsDTO.setRole(new ArrayList<>());
                userAdminRightsDTO.getRole().add(split[1]);
                user.add(userAdminRightsDTO);
            } else {
                userFromList.getRole().add(split[1]);
            }
//            System.out.println(user);
        }

        checkRoleLengthForEachUserIsLessThan2(user);

        saveDataFromUserDto(user);
    }

    private void checkRoleLengthForEachUserIsLessThan2(List<UserAdminRightsDTO> user) {
        Set<String> invalidUserNames = new HashSet<>();

        for (UserAdminRightsDTO dto:user) {
            if(dto.getRole().size()>1) {
                invalidUserNames.add(dto.getName());
            }
        }

        if (invalidUserNames.size() > 0) {
            throw new UserHasMoreThatOneRoleException(invalidUserNames);
        }
    }

    private void saveDataFromUserDto(List<UserAdminRightsDTO> users) {
        Set<String> invalidUserNames = new HashSet<>();

        for (UsersModel userEntity: userRepositiry.findAll()) {
            UserAdminRightsDTO roleDTO = getUserFromList(users, userEntity.getName());

            if (roleDTO != null) {
                if (roleDTO.getRole().size() > 0) {
                    if (roleDTO.getRole().size() == userEntity.getUserRoles().size()) {

                        if (roleDTO.getRole().get(0).equals(userEntity.getUserRoles().get(0))) {
                            //здесь все ок. Озн, что вносимая роль и роль в БД совпадают
                            //continue озн, что продолжаем цикл со след элемента
                            continue;
                        } else {
                            //Ошибка
                            misstake(roleDTO.getName(), invalidUserNames);
                        }

                    } else {
                        //означает что в БД нет роли у этого юзера
                        if (roleDTO.getRole().size() == 1) {
                            //сохранение в БД
                            saveInDB(roleDTO.getName(), roleDTO.getRole().get(0));
                            continue;
                        } else {
                            //Ошибка, т.к. озн, что поставленно нe одна птичка
                            misstake(roleDTO.getName(), invalidUserNames);
                        }
                    }
                }
            } else {
                saveInDB(userEntity.getName(), "");
            }
        }

        if (invalidUserNames.size() != 0) {
            throw new UserHasMoreThatOneRoleException(invalidUserNames);
        }
    }

    public void saveInDB(String name, String role) {
        UsersModel readUserFromDB = userRepositiry.findOne(name);
        UserRole roleEntity = userRoleRepositiry.findFirstByRole(role);
        if (roleEntity != null) {
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(roleEntity);
            readUserFromDB.setUserRoles(userRoles);
        } else {
            readUserFromDB.setUserRoles(new ArrayList<>());
        }

        userRepositiry.save(readUserFromDB);
    }

    public void misstake(String name, Set<String> invalidUserNames) {
        invalidUserNames.add(name);

    }


    public void addNewUser(final String name, final String password, final String email) {
        List<UsersModel> byName = userRepositiry.findByName(name);
        List<UsersModel> byEmail = userRepositiry.findByEmail(email);

        if (byName.size() != 0 ) {

            throw new NickNameCompareWithDBException();

        }

        if (byEmail.size() != 0 ) {

            throw new EmailCompareWithDBException();

        }
            UsersModel usersModel = new UsersModel();
            usersModel.setName(name);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            userRepositiry.save(usersModel);

    }
}
