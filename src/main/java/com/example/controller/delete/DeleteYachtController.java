package com.example.controller.delete;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import com.example.service.YachtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeleteYachtController {

    @Autowired
    private YachtsService yachtsService;

    @RequestMapping(value = "/deleteYacht/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteSelectedYacht(@RequestBody List<String> list, Model model) {

        yachtsService.deleteYachts(list);

        List<YachtsModel> all = yachtsService.vewAllYachts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewSelectedYacht", all);
        modelAndView.setViewName("yacht");
        return  modelAndView;
    }


}
