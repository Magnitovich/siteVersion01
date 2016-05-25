package com.example.controller.buy;

import com.example.model.YachtDTO;
import com.example.model.YachtsModel;
import com.example.service.YachtsService;

import org.springframework.beans.factory.annotation.Autowired;
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
    private YachtsService yachtsService;

    @RequestMapping(value = "buyYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewYachtForSale(@RequestParam("hiddenYachtName")String name
                                         ) {

            List<YachtDTO> list = yachtsService.viewSelectedYacht(name);
            ModelAndView modelAndView = viewYachSelected(list);
            return modelAndView;

    }
    @RequestMapping(value = "buyImgYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewYachtImgForSale(@RequestParam("hiddenYachtImg")String id) {
        System.out.println(id+ "\\\\\\\\//////////");

            List<YachtDTO> listImg = yachtsService.viewSelectedYacht(id);
            ModelAndView modelAndView = viewYachSelected(listImg);
            return modelAndView;

    }
    @ModelAttribute("baySuccessfulYachts")
    public YachtsModel createModel() {
        return new YachtsModel();
    }
    public ModelAndView viewYachSelected(List<YachtDTO> list){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewBuyYacht", list);
        modelAndView.setViewName("buy/buyYacht");
        return modelAndView;
    }
}
