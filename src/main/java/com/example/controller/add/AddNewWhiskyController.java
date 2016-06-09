package com.example.controller.add;

import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import com.example.model.YachtDTO;
import com.example.service.WhiskyService;
import com.example.service.exceptions.ExceptionAddWhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.LabelTag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AddNewWhiskyController {

    @Autowired
    private WhiskyService whiskyService;
    @Autowired
    private ExceptionAddWhiskyService exceptionAddWhiskyService;

    @Value("${img.whisky.path}")
    private String imagePath;

    @Value("${img.whisky.relative.path}")
    private String relativeImagePath;


    @RequestMapping(value = "addNewWhisky", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewPageAddWhisky(@RequestParam(required = false)String id) {

        System.out.println(id);
        if(id !=null && id.length() !=0) {
            WhiskyDTO model = whiskyService.viewByNameWhisky(id);
            model.setIdForEdit(id);
            System.out.println(model);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(model);
            modelAndView.addObject("understandEditOrAdd", model);
            modelAndView.setViewName("add/addWhisky");
            return modelAndView;

        } else {
            return viewAddForm();
        }
    }

    @RequestMapping(value = "addSuccessfulNewWhisky", method = {RequestMethod.POST})
    public ModelAndView viewAddWhisky(@ModelAttribute("understandEditOrAdd")WhiskyDTO whiskyDTO,
                                      BindingResult bindingResult ) throws IOException {

        if (whiskyDTO.getIdForEdit() != null && whiskyDTO.getIdForEdit().length() != 0) {

            whiskyService.editWhisky(whiskyDTO);
            List<WhiskyDTO> all = whiskyService.seeAllWhisky();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("viewAvailableWhisky", all);
            modelAndView.setViewName("whisky");
            return modelAndView;


        } else {

            FileOutputStream fos = null;

            try {

//                getOriginalFilename благодаря этой строке загрузится название файло которое было на UI
//                getFileObject() это находится в whiskyDTO

                File convFile = new File(imagePath + whiskyDTO.getFileObject().getOriginalFilename());
                if (!convFile.exists()) {
                    convFile.createNewFile();
                }
//                fos- запись
                 fos = new FileOutputStream(convFile);
                fos.write(whiskyDTO.getFileObject().getBytes());

                //nameFile получаем путь к файлу в БД
                String nameFile = relativeImagePath + whiskyDTO.getFileObject().getOriginalFilename();

                exceptionAddWhiskyService.compareInfoInDBWithInfoUI(nameFile, whiskyDTO.getNameWhisky(), whiskyDTO.getDescribeWhisky(),
                        whiskyDTO.getQuantityWhisky(), whiskyDTO.getPrice());

                List<WhiskyDTO> all = whiskyService.seeAllWhisky();

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("viewAvailableWhisky", all);
                modelAndView.setViewName("whisky");
                return modelAndView;
            } catch (RuntimeException r) {

                bindingResult.rejectValue("nameWhisky", "error.nameWhisky", "Error: Photo or name exist in DB");
                return viewAddForm();

            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
        finally {
                if(fos != null) {
                    fos.close();
                }
            }

        }

    }

    public ModelAndView viewAddForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add/addWhisky");
        return modelAndView;

    }


    @ModelAttribute("understandEditOrAdd")
    public WhiskyDTO createModel() {
        return new WhiskyDTO();
    }
}
