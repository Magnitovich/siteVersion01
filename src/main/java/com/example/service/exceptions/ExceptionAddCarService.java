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

        public void compareInfoInDBWithInfoUI(final String photo, final String name_Cars, final String describe,
                                          final Integer quantity, final BigDecimal price) {

        List<CarsDTO> list = carsService.viewPhotoName(photo, name_Cars);

        if (list.size()==0) {

            carsService.addNewCarsForDb(photo,name_Cars, describe,quantity,price);

        } else {
            throw new RuntimeException("WOW");
        }

    }


}
