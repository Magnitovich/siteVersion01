package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.service.WhiskyService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class SuccessfulBuyWhiskey {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private WhiskyService whiskyService;


    @RequestMapping(value = "buySuccessfulWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestBody ArrayList< WhiskyDTO> namesWhisky, Model model)
//            @RequestParam("nameSelectedWhisky")String name,
//                             @RequestParam("quantitySelectedWhisky")Integer quantityOrder)
    {
        System.out.println(namesWhisky);
//        for(WhiskyDTO i:namesWhisky) {
//            System.out.println("Name: = " + i.getNameWhisky());
//        System.out.println("Quantity: = "+i.getQuantityWhisky());
//            System.out.println("Price"+ i.getPrice());
//        }
//        System.out.println("Name: = "+name);
//        System.out.println("Quantity: = "+quantityOrder);
//        whiskyService.changeInfoInDB(name, quantityOrder);
//
//        List<WhiskyDTO> all = whiskyService.seeAllWhisky();
//
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("viewAvailableWhisky", all);
//        modelAndView.setViewName("whisky");
        return modelAndView;

    }
}
