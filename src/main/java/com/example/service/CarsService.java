package com.example.service;


import com.example.dao.CarsRepository;
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
        carsDTO.setDescriptions(carsMode.getDescriptions());
        carsDTO.setQuantity(carsMode.getQuantity());
        carsDTO.setPrice(carsMode.getPrice());

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
        carsModel.setPhoto(photo);
        carsModel.setName(name);
        carsModel.setDescriptions(describe);
        carsModel.setQuantity(quantity);
        carsModel.setPrice(price);
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
        model.setDescriptions(carsDTO.getDescriptions());
        model.setQuantity(carsDTO.getQuantity());
        model.setPrice(carsDTO.getPrice());

        carsRepository.save(model);

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
    public List<CarsDTO> viewPhotoName(String photo, String name){

        List<CarsModel> byName_carsAndPhoto = carsRepository.findByPhotoAndName(photo, name);
        List<CarsDTO> carsDTOs = convertListModelToLIstDTO(byName_carsAndPhoto);

        return carsDTOs;
    }

}
