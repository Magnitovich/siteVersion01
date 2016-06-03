package com.example.service.adminService;

import com.example.controller.CarsController;
import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepositiry;
import com.example.exception.UserHasMoreThatOneRoleException;
import com.example.model.RoleDto;
import com.example.model.UserAdminRightsDTO;
import com.example.model.UserRole;
import com.example.model.UsersModel;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

        userRepositiry.findAll().forEach(user -> {
            UserAdminRightsDTO dto = new UserAdminRightsDTO();
            dto.setName(user.getName());

            List<String> userRoles = new ArrayList<>();

            user.getUserRoles().forEach(role -> {
                userRoles.add(role.getRole());
            });
            dto.setRole(userRoles);

            List<RoleDto> userRoleDtos = new ArrayList<>();
            availableUserRoles.forEach(userRoleEntity -> {
                RoleDto roleDto = new RoleDto();
                String roleNameInDB = userRoleEntity.getRole();

                roleDto.setName(roleNameInDB);
                roleDto.setApplied(userRoles.contains(roleNameInDB));

                userRoleDtos.add(roleDto);
            });

            dto.setRoleDtos(userRoleDtos);
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
        for (UserAdminRightsDTO dto:user) {
            if(dto.getRole().size()>1) {
                throw new UserHasMoreThatOneRoleException();
            }

        }
    }

    private void saveDataFromUserDto(List<UserAdminRightsDTO> users) {
        misstake();
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
                            misstake();
                        }

                    } else {
                        //означает что в БД нет роли у этого юзера
                        if (roleDTO.getRole().size() == 1) {
                            //сохранение в БД
                            saveInDB(roleDTO.getName(), roleDTO.getRole().get(0));
                            continue;
                        } else {
                            //Ошибка, т.к. озн, что поставленно на одна птичка
                            misstake();
                        }
                    }
                }
            } else {
                saveInDB(userEntity.getName(), "");
            }
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

    public void misstake() {
        throw new UserHasMoreThatOneRoleException();
    }


    public void addNewUser(String name, String password) {
        List<UsersModel> byName = userRepositiry.findByName(name);
        if(byName.size()==0) {
            UsersModel usersModel = new UsersModel();
            usersModel.setName(name);
            usersModel.setPassword(password);
            userRepositiry.save(usersModel);
        } else {
            throw new RuntimeException("WOW");
        }

    }


}
