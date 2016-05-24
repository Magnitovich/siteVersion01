package com.example.service.exceptions;

import com.example.model.CarsDTO;
import com.example.model.WhiskyDTO;
import com.example.service.CarsService;
import com.example.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddWhiskyService {

    @Autowired
    private WhiskyService whiskyService;

        public void compareInfoInDBWithInfoUI(final String photo, final String nameWhisky, final String describe,
                                          final Integer quantity, final BigDecimal price) {

        List<WhiskyDTO> listPhoto = whiskyService.viewPhoto(photo);
        List<WhiskyDTO> listName = whiskyService.viewName(nameWhisky);

        if (listPhoto.size()==0 && listName.size()==0) {

            whiskyService.addNewWhisky(photo,nameWhisky, describe,quantity,price);

        } else {
            throw new RuntimeException("WOW");
        }

    }


}
