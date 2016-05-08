package com.example.controller;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YachtsController {

    @Autowired
    private YachtRepository yachtRepository;

    @RequestMapping(value = "selectAndBuyYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewListYachts() {

        Iterable<YachtsModel> all = yachtRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewSelectedYacht", all);
        modelAndView.setViewName("yacht");
        return  modelAndView;
    }
}
