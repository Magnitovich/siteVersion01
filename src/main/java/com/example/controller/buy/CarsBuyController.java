package com.example.controller.buy;


import com.example.model.CarsModel;
import com.example.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarsBuyController {

    @Autowired
    private CarsService buyCarService;

    @RequestMapping(value = "/bayCars", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewCarForBuy(@RequestParam("nameCarsHidden")String name_Cars) {
        System.out.println(name_Cars);
//        List<CarsModel> list = carsService.findByNameCars(name_Cars);
        List<CarsModel> list = buyCarService.viewSelectedCarForBuy(name_Cars);
        System.out.println(list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baySelectedCars", list);
        modelAndView.setViewName("buy/bayCars");
        return modelAndView;


    }
    @ModelAttribute("baySuccessfulCars")
    public CarsModel createModel() {
        return new CarsModel();
    }
}
