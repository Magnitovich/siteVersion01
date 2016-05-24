package com.example.controller.buy;

import com.example.model.YachtDTO;
import com.example.model.YachtsModel;
import com.example.service.YachtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SuccessfulBuyYachts {

    @Autowired
    private YachtsService yachtsService;

    @RequestMapping(value = "buyYachtSucceed", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewSuccessfulBuyYachts(@RequestParam("nameYacht")String name,
                                                @RequestParam("quantityYachts")Integer numberYachts) {
        System.out.println("Name Yachts: "+name+" quantity= "+numberYachts);
         yachtsService.buyAndUpdate(name, numberYachts);
        List<YachtDTO> all = yachtsService.vewAllYachts();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("buy/successful");
//        return modelAndView;
//        List<YachtsModel> all = yachtRepository.findAllYachts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewSelectedYacht", all);
        modelAndView.setViewName("yacht");
        return  modelAndView;
    }
}
