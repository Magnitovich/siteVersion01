package com.example.service.adminService;

import com.example.controller.CarsController;
import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepositiry;
import com.example.model.UserRole;
import com.example.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminRoleService {

    @Autowired
    private UserRepository userRepositiry;

    @Autowired
    private UserRoleRepositiry userRoleRepositiry;

    public void addRightsAdmin(List<String> roles, Integer number) {
        number = 4;

        UserRole userRole = new UserRole();
        UsersModel usersModel = new UsersModel();

        for (String role:roles) {

            userRole.setRole(role);
            userRoleRepositiry.save(userRole);
            usersModel.getUserRoles().add(userRole);

        }
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
