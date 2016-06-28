package com.example.service.exceptions;

import com.example.dao.CarsRepository;
import com.example.model.CarsDTO;
import com.example.model.CarsModel;
import com.example.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddCarService {

    @Autowired
    private CarsService carsService;

        public void compareInfoInDBWithInfoUI(final String photo, final String nameCars, final String describe,
                                          final Integer quantity, final BigDecimal price) {

        List<CarsDTO> listPhoto = carsService.viewPhoto(photo);
        List<CarsDTO> listName = carsService.viewPhoto(nameCars);

        if (listPhoto.size()==0 && listName.size()==0) {

            carsService.addNewCarsForDb(photo,nameCars, describe,quantity,price);

        } else {
            throw new RuntimeException("WOW");
        }

    }


}
