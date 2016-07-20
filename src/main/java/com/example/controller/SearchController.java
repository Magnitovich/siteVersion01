package com.example.controller;

import com.example.model.CarsDTO;
import com.example.model.WhiskyDTO;
import com.example.model.YachtDTO;
import com.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/searchOnTheSite", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewAllWhiskyInWarehouse(@RequestParam(value = "searchRequest", required = false)
                                                     String findSearchRequest) {
        System.out.println("We searching: " + findSearchRequest);

//        Iterable<WhiskyDTO> list = whiskyService.seeAllWhisky();
        List<WhiskyDTO> listWhisky = searchService.searchInWhisky(findSearchRequest);
        List<YachtDTO> yachtDTOList = searchService.searchInYachts(findSearchRequest);
        List<CarsDTO> carDTOList = searchService.searchInCar(findSearchRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("answerSearch", listWhisky);
        modelAndView.addObject("viewSelectedYacht", yachtDTOList);
        modelAndView.addObject("namesCars", carDTOList);
        modelAndView.setViewName("answerOnSearch");
        return modelAndView;
    }


}
