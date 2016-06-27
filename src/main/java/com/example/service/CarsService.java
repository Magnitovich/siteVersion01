package com.example.service;


import com.example.dao.CarsRepository;

import com.example.model.CarDescrModel;
import com.example.model.CarsDTO;
import com.example.model.CarsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    private CarsDTO conversModelToDTO(CarsModel carsMode) {

        CarsDTO carsDTO = new CarsDTO();

        carsDTO.setPhoto(carsMode.getPhoto());
        carsDTO.setName(carsMode.getName());
        carsDTO.setDescriptions(carsMode.getCarDescrModel().getDescription());
        carsDTO.setQuantity(carsMode.getCarDescrModel().getQuantity());
        carsDTO.setPrice(carsMode.getCarDescrModel().getPrice());

        return carsDTO;
    }

    public List<CarsDTO> viewAllModelCars() {

        List<CarsModel> allCars = carsRepository.findAllCars();
        List<CarsDTO> carsDTOs = convertListModelToLIstDTO(allCars);
        return carsDTOs;
    }

    public CarsDTO viewSelectedModelCars(String name) {

        CarsModel selectedCars = carsRepository.findFirstByName(name);
        CarsDTO carsDTOs = conversModelToDTO(selectedCars);
        return carsDTOs;
    }

    public void addNewCarsForDb(final String photo, final String name, final String describe, final Integer quantity,
                                final BigDecimal price) {
        CarsModel carsModel = new CarsModel();
        CarDescrModel carDescrModel = new CarDescrModel();
        carsModel.setPhoto(photo);
        carsModel.setName(name);
        carDescrModel.setDescription(describe);
        carDescrModel.setQuantity(quantity);
        carDescrModel.setPrice(price);
        carsModel.setCarDescrModel(carDescrModel);

        carsRepository.save(carsModel);
    }


    public List<CarsDTO> convertListModelToLIstDTO(List<CarsModel> byNameCars) {
        List<CarsDTO> carsDTOs = new ArrayList<>();
        for (CarsModel car:byNameCars ) {
            CarsDTO carsDTO = conversModelToDTO(car);
            carsDTOs.add(carsDTO);
        }
    return carsDTOs;
    }

    public List<CarsDTO> viewSelectedCarForBuy(final String nameCars) {

        List<CarsModel> byNameCars = carsRepository.findByNameCars(nameCars);
        List<CarsDTO> carsDTOs = convertListModelToLIstDTO(byNameCars);
        return carsDTOs;
    }
    @Transactional
    public void editCar(CarsDTO carsDTO) {

        CarsModel model = carsRepository.findFirstByName(carsDTO.getName());

        model.setPhoto(carsDTO.getPhoto());
        model.setName(carsDTO.getName());
        model.getCarDescrModel().setDescription(carsDTO.getDescriptions());
        model.getCarDescrModel().setQuantity(carsDTO.getQuantity());
        model.getCarDescrModel().setPrice(carsDTO.getPrice());

        carsRepository.save(model);

    }

    @Transactional
    public void changeQuantityCarsInDB(final String nameCars, final int quantityFromUI) {

        List<CarsModel> list = carsRepository.findByNameCars(nameCars);

        if (list.size()==0) {
            System.out.println("Db not FOUND THIS CAR");
        } else {
            CarsModel model = list.get(0);

            int quantityCarsInDB = model.getCarDescrModel().getQuantity();

            int result;

            result = quantityCarsInDB - quantityFromUI;

            model.getCarDescrModel().setQuantity(result);
            carsRepository.save(model);
        }
    }

    public void deleteCars(List<String> nameDeletedCar) {

        for (String selectedCarForDelete:nameDeletedCar) {

            carsRepository.delete(selectedCarForDelete);

        }

    }
    public List<CarsDTO> viewPhoto(String photo){

        List<CarsModel> byName_carsAndPhoto = carsRepository.findByPhoto(photo);
        List<CarsDTO> carsDTOs = convertListModelToLIstDTO(byName_carsAndPhoto);

        return carsDTOs;
    }

    public List<CarsDTO> viewName(String name){

        List<CarsModel> byName_carsAndPhoto = carsRepository.findByNameCars(name);
        List<CarsDTO> carsDTOs = convertListModelToLIstDTO(byName_carsAndPhoto);

        return carsDTOs;
    }

}
