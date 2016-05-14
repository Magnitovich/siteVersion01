package com.example.controller.add;

import com.example.dao.CarsRepository;
import com.example.model.CarsDTO;
import com.example.model.CarsModel;
import com.example.model.YachtsModel;
import com.example.service.CarsService;
import com.example.service.exceptions.ExceptionAddCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AddNewCarsController {

    @Autowired
    private CarsService carsService;

    @Autowired
    private ExceptionAddCarService exceptionAddCar;

    @RequestMapping(value = "/addInfoAboutNewCar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAdd(@RequestParam(required = false)String id) {

        if (id !=null && id.length()!=0) {
            CarsDTO carsModel = carsService.viewSelectedModelCars(id);
            carsModel.setIdForEditAdd(id);
            System.out.println(id);
            System.out.println(carsModel);
            ModelAndView model = new ModelAndView();
            model.addObject("comparePhotoNameCarWithDB", carsModel);
            model.setViewName("add/addCars");
            return model;
//            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add/addCars");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulNewCars", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameCarWithDB")CarsDTO carsDTO,
                                    BindingResult bindingResult ) {

            if (carsDTO.getIdForEditAdd() != null && carsDTO.getIdForEditAdd().length() !=0) {
                    carsService.editCar(carsDTO);

                List<CarsDTO> list = carsService.viewAllModelCars();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("namesCars", list);
                modelAndView.setViewName("cars");
                return modelAndView;


            } else {
                try {

                    exceptionAddCar.compareInfoInDBWithInfoUI(carsDTO.getPhoto(), carsDTO.getName(), carsDTO.getDescriptions(),
                        carsDTO.getQuantity(), carsDTO.getPrice());
                carsService.addNewCarsForDb(carsDTO.getPhoto(), carsDTO.getName(), carsDTO.getDescriptions(),
                        carsDTO.getQuantity(), carsDTO.getPrice());
                List<CarsDTO> list = carsService.viewAllModelCars();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("namesCars", list);
                modelAndView.setViewName("cars");
                return modelAndView;

            }catch(RuntimeException r){
                bindingResult.rejectValue("name", "error.name", "Errore: Photo or name exist in DB");
                return seePageAdd(carsDTO.getName());
            }
        }
    }

    @ModelAttribute("comparePhotoNameCarWithDB")
    public CarsDTO createModel() {
        return new CarsDTO();
    }
}

