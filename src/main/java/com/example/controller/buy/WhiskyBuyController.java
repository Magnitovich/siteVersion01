package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WhiskyBuyController {

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "buySelectedWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewSelectedWhisky(@RequestParam(value = "nameBuyWhiskey", required = false)Long nameId
                                           ) {

//        System.out.println("@RequestParam(required = false)Long id - " + id);

        System.out.println(nameId);
        String s = whiskyService.convertIdToName(nameId);

        List<WhiskyDTO> list = whiskyService.viewSelectedWhisky(s);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("buySelectedWhisky", list);
        modelAndView.setViewName("buy/buyWhisky");
        return modelAndView;

    }
//    @RequestMapping(value = "buySelectedWhisky", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView viewSelectedWhisky(@RequestParam String nameWhisky) {
//
//        System.out.println(nameWhisky);
////        List<WhiskyDTO> list = whiskyService.viewSelectedWhisky(nameWhisky);
//            ModelAndView modelAndView = new ModelAndView();
////        modelAndView.addObject("buySelectedWhisky", list);
////        modelAndView.setViewName("buy/buyWhisky");
//            return modelAndView;
//        }
    }

