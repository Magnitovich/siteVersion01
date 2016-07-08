package com.example.controller.add;

import com.example.dao.CarsRepository;
import com.example.model.CarsDTO;
import com.example.model.CarsModel;
import com.example.model.YachtsModel;
import com.example.service.CarsService;
import com.example.service.exceptions.ExceptionAddCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AddNewCarsController {

    @Autowired
    private CarsService carsService;

    @Autowired
    private ExceptionAddCarService exceptionAddCar;

    @Value("${img.car.path}")
    private String realObjectsPath;

    @Value("${img.car.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewCar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAdd(@RequestParam(required = false)Long id) {

        if (id !=null ) {
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
                                    BindingResult bindingResult ) throws IOException {

        System.out.println(carsDTO.getName()+ "  PHOTO:=" + carsDTO.getObjectPhotoCar().getOriginalFilename());

        String nameFile = null;
        FileOutputStream fileOutputStream = null;

        if(!carsDTO.getObjectPhotoCar().isEmpty()) {

            File convertFileCar = new File(realObjectsPath + carsDTO.getObjectPhotoCar().getOriginalFilename());

            if (!convertFileCar.exists()) {
                convertFileCar.createNewFile();
            }
            fileOutputStream = new FileOutputStream(convertFileCar);
            fileOutputStream.write(carsDTO.getObjectPhotoCar().getBytes());

            nameFile = relativeObjectsPath + carsDTO.getObjectPhotoCar().getOriginalFilename();
        }
            if (carsDTO.getIdForEditAdd() != null ) {
                carsDTO.setPhoto(nameFile);
                carsService.editCar(carsDTO);

                List<CarsDTO> list = carsService.viewAllModelCars();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("namesCars", list);
                modelAndView.setViewName("cars");
                return modelAndView;


            } else {



                try {


                    exceptionAddCar.compareInfoInDBWithInfoUI(nameFile, carsDTO.getName(), carsDTO.getDescriptions(),
                            carsDTO.getQuantity(), carsDTO.getPrice());

//                    carsService.addNewCarsForDb(carsDTO.getPhoto(), carsDTO.getName(), carsDTO.getDescriptions(),
//                            carsDTO.getQuantity(), carsDTO.getPrice());
                    List<CarsDTO> list = carsService.viewAllModelCars();
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.addObject("namesCars", list);
                    modelAndView.setViewName("cars");
                    return modelAndView;

                } catch (RuntimeException r) {
                    System.out.println(r);
                    bindingResult.rejectValue("name", "error.name", "Errore: Photo or name exist in DB");
                    return viewException();
                } finally {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            }}
    public ModelAndView viewException(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add/addCars");
        return modelAndView;
    }

    @ModelAttribute("comparePhotoNameCarWithDB")
    public CarsDTO createModel() {
        return new CarsDTO();
    }
}

