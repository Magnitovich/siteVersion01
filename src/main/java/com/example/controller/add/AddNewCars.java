package com.example.controller.add;

import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
import com.example.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AddNewCars {

    @Autowired
    private CarsService carsService;

    @Autowired
    private CarsRepository carsRepository;

    @RequestMapping(value = "/addInfoAboutNewCar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add/addCars");
        return modelAndView;
    }

    @RequestMapping(value = "/addSuccessfulNewCars", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@RequestParam("photoCars")String photo,
                            @RequestParam("nameCars")String name,
                            @RequestParam("describeCars")String describe,
                            @RequestParam("quantityCars")Integer quantity,
                            @RequestParam("priceCars")BigDecimal price) {

        carsService.addNewCarsForDb(photo, name, describe, quantity, price);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("add/succeed");
//        return modelAndView;
        List<CarsModel> list = carsRepository.findAllCars();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesCars", list);
        modelAndView.setViewName("cars");
        return modelAndView;
    }
}
