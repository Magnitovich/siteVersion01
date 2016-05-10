package com.example.service;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class YachtAddService {

    @Autowired
    private YachtRepository yachtRepository;

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
