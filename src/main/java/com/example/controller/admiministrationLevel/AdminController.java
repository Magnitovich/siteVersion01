package com.example.controller.admiministrationLevel;

import com.example.dao.UserRepository;
import com.example.model.UsersModel;
import com.example.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping(value = "/administrationNotSleeps", method = RequestMethod.GET)
    public ModelAndView viewAdminRight() {

        Iterable<UsersModel> usersModels = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesUsersInDB", usersModels);
        modelAndView.setViewName("adminRight");
        return modelAndView;

    }
    private String[] split;
    @RequestMapping(value ="/okYouDoIt",  method ={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAdminChange(
            @RequestBody List<String> list, Model model, HttpServletRequest req

//            @RequestParam(required = false)String id
    ) {
        adminRoleService.addRightsAdmin(list);

//        for(String i:list) {
//
//            System.out.println(i);
//            split = i.split("_");
//            System.out.println(split[0]);
//            System.out.println(split[1]);
//
//        }

//        System.out.println();
//        System.out.println(split[0]);
//        System.out.println(name);
        System.out.println(list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fist");
        return modelAndView;

    }

}
