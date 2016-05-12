package com.example.service.exceptions;

import com.example.controller.YachtsController;
import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
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
    private YachtAddService yachtAddService;
    @Autowired
    private YachtsController yachtsController;

    public void compareEnterInfoAndInDB(final String photo, final String name, final String describe,
                                                     final Integer quantity, final BigDecimal price) {

        List<YachtsModel> list = repository.findByNameAndPhoto(photo, name);
        if (list.size()==0) {

            yachtAddService.addNewYacht(photo,name,describe,quantity,price);
//            ModelAndView modelAndView = yachtsController.viewListYachts();

        } else {
            throw new RuntimeException("WOW");
        }
    }

}
