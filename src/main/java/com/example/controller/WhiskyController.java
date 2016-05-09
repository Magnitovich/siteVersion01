package com.example.controller;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WhiskyController {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @RequestMapping(value = "/warehouseWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAllWhiskyInWarehouse() {

        Iterable<WhiskeyModel> all = whiskyRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", all);
        modelAndView.setViewName("whisky");
        return modelAndView;
    }
}
