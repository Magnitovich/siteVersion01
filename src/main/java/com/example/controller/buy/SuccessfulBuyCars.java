package com.example.controller.buy;

import com.example.dao.CarsRepository;
import com.example.model.CarsDTO;
import com.example.model.CarsModel;

import com.example.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SuccessfulBuyCars {
    @Autowired
    private CarsService buyCarService;

    @Autowired
    private CarsRepository carsRepository;

    @RequestMapping(value = "buySuccessful", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewBuyCars(@RequestParam("quantityCars")Integer quantityBuyCar,
                                    @RequestParam("nameCars")String nameBuyCar) {

        System.out.println("Имя авто: "+nameBuyCar+" Кол-во авто: "+quantityBuyCar);

         buyCarService.changeQuantityCarsInDB(nameBuyCar, quantityBuyCar);

        List<CarsDTO> list = buyCarService.viewAllModelCars();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesCars", list);
        modelAndView.setViewName("cars");
        return modelAndView;


    }
}
