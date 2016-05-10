package com.example.controller.delete;

import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
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
public class DeleteCarsController {

    @Autowired
    private CarsRepository carsRepository;

    @RequestMapping(value = "/deleteCars/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewDeleteCarResult(@RequestBody List<String> namesCars, Model model,
                                            HttpServletRequest req) {
        System.out.println(namesCars);
        for (String cars:namesCars) {
            System.out.println(cars);

            carsRepository.delete(cars);

        }
        List<CarsModel> list = carsRepository.findAllCars();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namesCars", list);
        modelAndView.setViewName("cars");
        return modelAndView;

    }


}
