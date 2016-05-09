package com.example.controller.add;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import com.example.service.ExceptionAddYachtService;
import com.example.service.YachtAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

;import java.math.BigDecimal;
import java.util.List;


@Controller
public class AddNewYachtsController {

    @Autowired
    private YachtAddService yachtAddService;

    @Autowired
    private YachtRepository seeAllYachts;

    @Autowired
    private ExceptionAddYachtService exceptionAddYachtService;

    @RequestMapping(value = "/addInfoAboutNewYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddYachts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add/addYachts");
        return modelAndView;
    }

    @RequestMapping(value = "/addSuccessfulYacht", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("regPage")YachtsModel yachtsModel, BindingResult result,
                                    @RequestParam("photoYacht")String photo,
                                    @RequestParam("nameYacht")String name,
                                    @RequestParam("describeYacht")String describe,
                                    @RequestParam("quantityYacht")Integer quantity,
                                    @RequestParam("priceYacht")BigDecimal price) {
        try {
//        yachtAddService.addNewYacht(photo, name, describe, quantity, price);

            exceptionAddYachtService.compareEnterInfoAndInDB(photo, name, describe, quantity, price);

            List<YachtsModel> list = seeAllYachts.findAllYachts();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("viewSelectedYacht", list);
            modelAndView.setViewName("yacht");
            return modelAndView;
        } catch (RuntimeException r) {

            result.rejectValue("exceptionCompare", "error.regPage", "Errore: Login exist");
            return seePageAddYachts();

        }
    }

    @ModelAttribute("comparePhotoNameWithDB")
    public YachtsModel createModel() {
        return new YachtsModel();
    }
}
