package com.example.controller;

import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.LabelTag;

import java.util.List;

@Controller
public class CarsController {

    @Autowired
    private CarsRepository carsRepository;

    @RequestMapping(value = "/saleCars", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewCarsForSale() {
        List<CarsModel> list = carsRepository.findAllCars();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesCars", list);
        modelAndView.setViewName("cars");
        return modelAndView;

    }
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView delete(@RequestParam("name_Cars")List<String> names) {
        System.out.println(names);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add/succeed");
        return modelAndView;
    }

//    @RequestMapping(value = "/deleteCars", method = {RequestMethod.GET, RequestMethod.POST})
//    public void deleteSelectedModel(@RequestParam("CheckBoxDelete")String nameCarsSelected) {
//        System.out.println(nameCarsSelected);
//
//
//    }

}
