package com.example.controller.buy;


import com.example.model.CarsDTO;
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
    public ModelAndView viewCarForBuy(@RequestParam("nameCarsHidden")Long idCars) {
        System.out.println(idCars);
//        List<CarsModel> list = carsService.findByNameCars(name_Cars);
        String s = buyCarService.convertLongToString(idCars);
        List<CarsDTO> list = buyCarService.viewSelectedCarForBuy(s);
        System.out.println(list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baySelectedCars", list);
        modelAndView.setViewName("buy/bayCars");
        return modelAndView;


    }
    @ModelAttribute("baySuccessfulCars")
    public CarsDTO createModel() {
        return new CarsDTO();
    }
}
