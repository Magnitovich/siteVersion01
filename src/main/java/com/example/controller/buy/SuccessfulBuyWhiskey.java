package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SuccessfulBuyWhiskey {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "buySuccessfulWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestParam("nameSelectedWhisky")String name,
                             @RequestParam("quantitySelectedWhisky")Integer quantityOrder) {

        System.out.println("Name: = "+name);
        System.out.println("Quantity: = "+quantityOrder);
        whiskyService.changeInfoInDB(name, quantityOrder);

        List<WhiskyDTO> all = whiskyService.seeAllWhisky();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", all);
        modelAndView.setViewName("whisky");
        return modelAndView;

    }
}
