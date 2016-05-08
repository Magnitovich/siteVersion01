package com.example.service;

import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BuyCarService {

    @Autowired
    private CarsRepository carsRepository;

    public List<CarsModel> viewSelectedCarForBuy(final String nameCars) {

        List<CarsModel> byNameCars = carsRepository.findByNameCars(nameCars);
        return byNameCars;
    }

//    public int quantityCarsInDb(final String nameCars) {

//        List<CarsModel> list = viewSelectedCarForBuy(nameCars);
//
//        if (list.size()==0) {
//            System.out.println("Db not FOUND THIS CAR");
//            return 0;
//        } else {
//            CarsModel model = list.get(0);
//            int quantityCarsInDB = model.getQuantity();
//            return quantityCarsInDB;
//        }
//    }
    @Transactional
    public void changeQuantityCarsInDB(final String nameCars, final int quantityFromUI) {

        List<CarsModel> list = carsRepository.findByNameCars(nameCars);

        if (list.size()==0) {
            System.out.println("Db not FOUND THIS CAR");
        } else {
            CarsModel model = list.get(0);
            int quantityCarsInDB = model.getQuantity();

            int result;

            result = quantityCarsInDB - quantityFromUI;

            model.setQuantity(result);
            carsRepository.save(model);

        }
    }

}
