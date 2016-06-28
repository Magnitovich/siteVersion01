package com.example.controller.admiministrationLevel;

import com.example.dao.UserRepository;
import com.example.exception.UserHasMoreThatOneRoleException;
import com.example.model.UserAdminRightsDTO;
import com.example.model.UsersModel;
import com.example.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

            adminRoleService.addRightsAdmin(list);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fist");
        return modelAndView;

    }

    @ExceptionHandler
    void handleUserHasMoreThatOneRoleException(UserHasMoreThatOneRoleException e, HttpServletResponse response) throws IOException {
        Set<String> invalidUserNames = e.getInvalidUserNames();
        response.sendError(HttpStatus.EXPECTATION_FAILED.value(), StringUtils.collectionToCommaDelimitedString(invalidUserNames));
    }
}
