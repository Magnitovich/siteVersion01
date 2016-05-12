package com.example.service;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class YachtsService {

    @Autowired
    private YachtRepository yachtRepository;

    public List<YachtsModel> vewAllYachts() {

        List<YachtsModel> allYachts = yachtRepository.findAllYachts();
        return allYachts;
    }

    public void deleteYachts(List<String> nameYachts) {

        for (String yacht:nameYachts) {
            yachtRepository.delete(yacht);
        }
    }
    public List<YachtsModel> viewSelectedYacht(String name) {

        List<YachtsModel> byNameYacht = yachtRepository.findByNameYacht(name);
        return byNameYacht;
    }

    @Transactional
    public void buyAndUpdate(String name, Integer quantityUI) {

        List<YachtsModel> listYacht = yachtRepository.findByNameYacht(name);
        if (listYacht.size() == 0) {
            System.out.println("EXCEPTION YACHT NOT FOUND");
        } else {
            YachtsModel model = listYacht.get(0);
            int quantityYachtInDB = model.getNumber();
            int result = quantityYachtInDB - quantityUI;
            model.setNumber(result);
            yachtRepository.save(model);
        }

    }
    public void addNewYacht(final String photo, final String name, final String describe,
                            final Integer quantity, final BigDecimal price) {
        YachtsModel yachtsModel = new YachtsModel();
        yachtsModel.setPhoto(photo);
        yachtsModel.setName(name);
        yachtsModel.setDescriptions(describe);
        yachtsModel.setNumber(quantity);
        yachtsModel.setPrice(price);
        yachtRepository.save(yachtsModel);
    }

    public YachtsModel editYacht(String nameYacht) {

        List<YachtsModel> list = yachtRepository.findByNameYacht(nameYacht);
        YachtsModel model = list.get(0);
        model.getPhoto();
        model.getName();
        model.getDescriptions();
        model.getNumber();
        model.getPrice();

        return model;
    }

}
