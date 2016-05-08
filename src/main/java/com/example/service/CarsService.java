package com.example.service;


import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    public void addNewCarsForDb(final String photo, final String name, final String describe, final Integer quantity,
                                final BigDecimal price) {
        CarsModel carsModel = new CarsModel();
        carsModel.setPhoto(photo);
        carsModel.setName_Cars(name);
        carsModel.setDescriptions(describe);
        carsModel.setQuantity(quantity);
        carsModel.setPrice(price);
        carsRepository.save(carsModel);
    }

    public void deleteCars(String name) {
        carsRepository.delete(name);
    }
    public void viewSelectedCarsForBuy(final String name) {
        carsRepository.findByNameCars(name);
    }




}
