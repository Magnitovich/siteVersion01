package com.example.controller.edit;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import com.example.service.YachtAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class EditYachts {

    @Autowired
    private YachtAddService yachtService;
    @Autowired
    private YachtRepository yachtRepository;

    @RequestMapping(value = "/editSelectedYacht", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewEditPage(@RequestParam(required = false)String id) {

        System.out.println(id);
        YachtsModel yachtsModel = yachtService.editYacht(id);

        System.out.println(yachtsModel);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("selectedYacht", yachtsModel);
        modelAndView.setViewName("edit/editYachts");
        return modelAndView;

    }

    @RequestMapping(value = "editSuccessfulYachts", method = RequestMethod.POST)
    public ModelAndView viewFinalEdit(@RequestParam("photoYacht")String photo,
                                      @RequestParam("nameYacht")String name,
                                      @RequestParam("describeYacht")String describe,
                                      @RequestParam("quantityYacht")Integer quantity,
                                      @RequestParam("priceYacht")BigDecimal price){

        yachtService.addNewYacht(photo, name, describe, quantity, price);
        List<YachtsModel> list = yachtRepository.findAllYachts();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewSelectedYacht", list);
        modelAndView.setViewName("yacht");
        return modelAndView;

    }
}
