package com.example.service.adminService;

import com.example.controller.CarsController;
import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepositiry;
import com.example.model.UserAdminRightsDTO;
import com.example.model.UserRole;
import com.example.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        }
        for (UserAdminRightsDTO dto:user) {
//            System.out.println(dto.getName()+ "/////"+ dto.getRole().toArray().toString());

            if(dto.getRole().size()>1) {
                throw new RuntimeException("WOW");
            }

        }
        for (UserAdminRightsDTO roleDTO:user) {


            UsersModel byName = userRepositiry.findOne(roleDTO.getName());
            if (roleDTO.getRole().size() > 0) {
                if (roleDTO.getRole().size() == byName.getUserRoles().size()) {

                    if (roleDTO.getRole().get(0).equals(byName.getUserRoles().get(0))) {
                        //здесь все ок. Озн, что вносимая роль и роль в БД совпадают
                        //continue озн, что продолжаем цикл со след элемента
                        continue;

                    } else {
                        //Ошибка

                    }

                } else {
                    //означает что в БД нет роли у этого юзера
                    if (roleDTO.getRole().size() == 1) {
                        //сохранение в БД
                        saveInDB(roleDTO.getName(), roleDTO.getRole().get(0));

                    } else {
                        //Ошибка, т.к. озн, что поставленно на одна птичка
                    }
                }
            }
        }
        }
    public void saveInDB(String name, String role) {

        UsersModel readUserFromDB = userRepositiry.findOne(name);
            UserRole roleEntity = userRoleRepositiry.findFirstByRole(role);
            readUserFromDB.getUserRoles().add(roleEntity);

//            Long i = Long.parseLong(split[1]);

            userRepositiry.save(readUserFromDB);
    }

    public void misstake() {

        throw  new RuntimeException("WOW");
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
