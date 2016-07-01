package com.example.controller.add;

import com.example.dao.YachtRepository;
import com.example.model.YachtDTO;
import com.example.model.YachtsModel;
import com.example.service.YachtsService;
import com.example.service.exceptions.ExceptionAddYachtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

;import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Controller
public class AddNewYachtsController {

    @Autowired
    private YachtsService yachtService;

    @Autowired
    private YachtRepository seeAllYachts;

    @Autowired
    private ExceptionAddYachtService exceptionAddYachtService;

    @Value("${img.yacht.path}")
    private String realObjectsPath;

    @Value("${img.yacht.relative.path}")
    private String relativeObjectsPath;


    @RequestMapping(value = "/addInfoAboutNewYachts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddYachts(@RequestParam(required = false) String id) {

        if (id != null && id.length() != 0) {
            YachtDTO yachtDTO = yachtService.viewSelecterYachtModel(id);
            yachtDTO.setIdForEdit(id);
            System.out.println(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("comparePhotoNameWithDB", yachtDTO);
            modelAndView.setViewName("add/addYachts");
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add/addYachts");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulYacht", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") YachtDTO yachtDTO,
                                    BindingResult result,
                                    @RequestParam(required = false) String id) throws IOException {

        String nameFile = null;
        FileOutputStream fileOutputStream = null;

        if (!yachtDTO.getObjectPhotoYacht().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    yachtDTO.getObjectPhotoYacht().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(yachtDTO.getObjectPhotoYacht().getBytes());

            nameFile = relativeObjectsPath + yachtDTO.getObjectPhotoYacht().getOriginalFilename();

        }

        if (yachtDTO.getIdForEdit() != null && yachtDTO.getIdForEdit().length() != 0) {
            yachtDTO.setPhoto(nameFile);
            yachtService.editYacht(yachtDTO);
            List<YachtDTO> yachtDTOs = yachtService.vewAllYachts();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("viewSelectedYacht", yachtDTOs);
            modelAndView.setViewName("yacht");
            return modelAndView;

        } else {
            System.out.println(yachtDTO.getName() + "//PHOTO:= " + yachtDTO.getObjectPhotoYacht().getOriginalFilename());

            try {

                exceptionAddYachtService.compareEnterInfoAndInDB(nameFile, yachtDTO.getName(),
                        yachtDTO.getDescriptions(), yachtDTO.getNumber(), yachtDTO.getPrice());

                List<YachtDTO> list = yachtService.vewAllYachts();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("viewSelectedYacht", list);
                modelAndView.setViewName("yacht");
                return modelAndView;
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//            return seePageAddYachts(yachtDTO.getName());
                return viewExeption();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public ModelAndView viewExeption() {

        ModelAndView andView = new ModelAndView();
        andView.setViewName("add/addYachts");
        return andView;

    }

    @ModelAttribute("comparePhotoNameWithDB")
    public YachtDTO createModel() {
        return new YachtDTO();
    }
}
