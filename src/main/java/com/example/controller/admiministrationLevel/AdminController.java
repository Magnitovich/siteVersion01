package com.example.controller.admiministrationLevel;

import com.example.dao.UserRepository;
import com.example.model.UserAdminRightsDTO;
import com.example.model.UsersModel;
import com.example.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

        List<UserAdminRightsDTO> adminRights = adminRoleService.getAdminRights();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("adminRightsModel", adminRights);
        modelAndView.setViewName("adminRight");
        return modelAndView;

    }

    @RequestMapping(value ="/okYouDoIt",  method ={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAdminChange(
            @RequestBody List<String> list, HttpServletRequest req ) {
        //try {
            adminRoleService.addRightsAdmin(list);
        /*} catch (RuntimeException e) {
            List<UserAdminRightsDTO> adminRights = adminRoleService.getAdminRights();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errors", "You are managing in a wrong way!");
            modelAndView.addObject("adminRightsModel", adminRights);
            modelAndView.setViewName("adminRight");
            return modelAndView;
        }*/

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fist");
        return modelAndView;

    }

}
