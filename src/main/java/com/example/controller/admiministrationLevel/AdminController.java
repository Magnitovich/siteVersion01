package com.example.controller.admiministrationLevel;

import com.example.dao.UserRepository;
import com.example.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/administrationNotSleeps", method = RequestMethod.GET)
    public ModelAndView viewAdminRight() {

        Iterable<UsersModel> usersModels = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesUsers", usersModels);
        modelAndView.setViewName("adminRight");
        return modelAndView;

    }


}
