package com.example.controller.delete;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeleteWhiskyController {

    @Autowired
    private WhiskyService whiskyService;

    @RequestMapping(value = "/deleteWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewDelete(@RequestBody List<String> namesWhisky, Model model,
                                   HttpServletRequest req) {

        whiskyService.delete(namesWhisky);
        Iterable<WhiskeyModel> whiskeyModels = whiskyService.seeAllWhisky();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(namesWhisky);
        modelAndView.addObject("viewAvailableWhisky", whiskeyModels);
        modelAndView.setViewName("whisky");
        return modelAndView;

    }


}
