package com.example.controller;

import com.example.model.WhiskeyModel;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WhiskyController {

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "/warehouseWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAllWhiskyInWarehouse() {

        Iterable<WhiskeyModel> list = whiskyService.seeAllWhisky();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", list);
        modelAndView.setViewName("whisky");
        return modelAndView;
    }



}
