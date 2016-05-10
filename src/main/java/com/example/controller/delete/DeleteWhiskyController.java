package com.example.controller.delete;

import com.example.dao.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeleteWhiskyController {

    @Autowired
    private WhiskyRepository repository;

    @RequestMapping(value = "/deleteWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewDelete(@RequestBody List<String> namesWhisky, Model model,
                                   HttpServletRequest req) {

        for (String names:namesWhisky) {

            repository.delete(names);

        }

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(namesWhisky);

        return modelAndView;

    }


}
