package com.example.controller.buy;

import com.example.dao.YachtRepository;
import com.example.model.CarsModel;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class YachtsBuyController {

    @Autowired
    private YachtRepository yachtRepository;

    @RequestMapping(value = "buyYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewYachtForSale(@RequestParam("hiddenYachtName")String name) {

        List<YachtsModel> list = yachtRepository.findByNameYacht(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewBuyYacht", list);
        modelAndView.setViewName("buy/buyYacht");
        return modelAndView;
    }
    @ModelAttribute("baySuccessfulYachts")
    public YachtsModel createModel() {
        return new YachtsModel();
    }
}
