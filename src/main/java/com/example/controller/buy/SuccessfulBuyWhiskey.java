package com.example.controller.buy;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.service.old.BuyWhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SuccessfulBuyWhiskey {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private BuyWhiskyService whiskyService;

    @RequestMapping(value = "buySuccessfulWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestParam("nameWhiskey")String name,
                             @RequestParam("numberOrderWhisky")Integer quantityOrder) {

        whiskyService.changeInfoInDB(name, quantityOrder);

        Iterable<WhiskeyModel> all = whiskyRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewAvailableWhisky", all);
        modelAndView.setViewName("whisky");
        return modelAndView;

    }
}
