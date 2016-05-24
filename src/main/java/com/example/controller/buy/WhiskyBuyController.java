package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WhiskyBuyController {

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "buySelectedWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewSelectedWhisky(@RequestParam("nameBuyWhiskey")String nameWhisky) {

        System.out.println(nameWhisky);
        List<WhiskyDTO> list = whiskyService.viewSelectedWhisky(nameWhisky);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("buySelectedWhisky", list);
        modelAndView.setViewName("buy/buyWhisky");
        return modelAndView;


    }
}
