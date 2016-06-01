package com.example.controller;

import com.example.dao.UserRepository;
import com.example.model.UsersModel;
import com.example.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView viewMainPage() {
//        List<String> byName = userRepository.findByName();
//        System.out.println(byName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registrationPage(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "password") String password) {

        try {
            adminRoleService.addNewUser(name, password);
//            adminRoleService.addNewUser(model.getName(), model.getPassword());

            System.out.println(name + " pass:= " + password);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("fist");
            return modelAndView;

        } catch (RuntimeException r) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }
}
