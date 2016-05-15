package com.example.service.exceptions;

import com.example.controller.YachtsController;
import com.example.dao.YachtRepository;
import com.example.model.YachtDTO;
import com.example.model.YachtsModel;
import com.example.service.YachtsService;
import com.example.service.old.YachtAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddYachtService {

    @Autowired
    private YachtRepository repository;
    @Autowired
    private YachtsService yachtService;
    @Autowired
    private YachtsController yachtsController;

    public void compareEnterInfoAndInDB(final String photo, final String name, final String describe,
                                                     final Integer quantity, final BigDecimal price) {

        List<YachtDTO> list = yachtService.viewPhotoAndYacht(photo, name);
        if (list.size()==0) {

            yachtService.addNewYacht(photo,name,describe,quantity,price);
//            ModelAndView modelAndView = yachtsController.viewListYachts();

        } else {
            throw new RuntimeException("WOW");
        }
    }

}
