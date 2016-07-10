package com.example.controller;

import com.example.config.UserDetailsImpl;
import com.example.dao.UserRepository;
import com.example.model.UsersModel;
import com.example.service.SendingMail;
import com.example.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SendingMail sendingMail;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView viewMainPage() {
//        List<String> byName = userRepository.findByName();
//        System.out.println(byName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registrationPage(
                                         @RequestParam(value = "NickName") String name,
                                         @RequestParam(value = "signUpPassword") String password,
                                         @RequestParam(value = "Email") String email) throws MessagingException {

        System.out.println("Nick= "+name+" pass= "+password+" email= "+email);

        adminRoleService.addNewUser(name, password, email);

        sendingMail.sendingMessage(email);

        System.out.println(name + " pass:= " + password+ " email: "+email);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fist");
        return modelAndView;
    }

    @RequestMapping(value = "/name_duplication", method = RequestMethod.GET)
    @ResponseBody
    public String checkDuplicates(@RequestParam(value="name") String name) {
        return userDetailsService.loadUserByUsername(name) == null ? "0" : "1";
    }

}
