package com.example.service;


import com.example.dao.CarsRepository;
import com.example.model.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    public List<CarsModel> viewAllModelCars() {

        List<CarsModel> allCars = carsRepository.findAllCars();
        return allCars;
    }

    public List<CarsModel> viewSelectedModelCars(String name) {

        List<CarsModel> selectedCars = carsRepository.findByNameCars(name);
        return selectedCars;
    }

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


    public List<CarsModel> viewSelectedCarForBuy(final String nameCars) {

        List<CarsModel> byNameCars = carsRepository.findByNameCars(nameCars);
        return byNameCars;
    }
    public CarsModel editCar(final String nameCar) {

        List<CarsModel> byNameCars = carsRepository.findByNameCars(nameCar);
        CarsModel model = byNameCars.get(0);
        model.getPhoto();
        model.getName_Cars();
        model.getDescriptions();
        model.getQuantity();
        model.getPrice();
        return model;
    }

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

    public void deleteCars(List<String> nameDeletedCar) {

        for (String selectedCarForDelete:nameDeletedCar) {

            carsRepository.delete(selectedCarForDelete);

        }

    }

}
