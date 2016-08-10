package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.service.WhiskyService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
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

    @JsonProperty(value = "dataArray")

    @RequestMapping(value = "/buySuccessfulWhisky", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView view(@RequestBody List<String> newArray, Model model) {
        System.out.println(newArray);
        System.out.println();
//        for (String properties : newArray) {
//            String[] split = properties.split("_");
//            String name = split[0];
//            Integer quantity = Integer.valueOf(split[1]);
//            System.out.println("name:= " + name);
//            System.out.println("quantity:= " + quantity);
//
////        whiskyService.changeInfoInDB(name, quantity);
//        }
        List<WhiskyDTO> whiskyDTOs = whiskyService.seeAllWhisky();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", whiskyDTOs);
        modelAndView.setViewName("whisky");
        return modelAndView;
    }

}