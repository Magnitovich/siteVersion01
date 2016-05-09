package com.example.controller.buy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Contended;

@Controller
public class SuccessfulBuyWhiskey {

    @RequestMapping(value = "buySuccessfulWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestParam("nameWhiskey")String name,
                             @RequestParam("numberOrderWhisky")Integer quantityOrder) {

        System.out.println("name Whisky "+name+" Quantity Order "+quantityOrder);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buy/successful");
        return modelAndView;

    }
}
