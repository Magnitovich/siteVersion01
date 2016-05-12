package com.example.controller.add;

import com.example.model.WhiskeyModel;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class AddNewWhiskyController {

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "addNewWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewPageAddWhisky(@RequestParam(required = false)String id) {

        System.out.println(id);
        if(id !=null && id.length() !=0) {
            WhiskeyModel model = whiskyService.editWhisky(id);
            System.out.println(model);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(model);
            modelAndView.setViewName("add/addWhisky");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add/addWhisky");
            return modelAndView;
        }
    }

    @RequestMapping(value = "addSuccessfulNewWhisky", method = {RequestMethod.POST})
    public ModelAndView viewAddWhisky(@RequestParam("photoWhisky")String photo,
                                      @RequestParam("nameWhisky")String name,
                                      @RequestParam("describeWhisky")String describe,
                                      @RequestParam("quantityWhisky")Integer quantity,
                                      @RequestParam("priceYacht")BigDecimal price) {

        whiskyService.addNewWhisky(photo, name, describe, quantity, price);

        Iterable<WhiskeyModel> all = whiskyService.seeAllWhisky();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", all);
        modelAndView.setViewName("whisky");
        return modelAndView;

    }
}
