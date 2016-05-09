package com.example.service;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AddWhiskyService {

    @Autowired
    private WhiskyRepository repository;

    public void addNewWhisky(final String photo, final String name, final String describe,
                             final Integer quantity, BigDecimal price) {

        WhiskeyModel whiskeyModel = new WhiskeyModel();
        whiskeyModel.setPhoto(photo);
        whiskeyModel.setNameWhisky(name);
        whiskeyModel.setDescribeWhisky(describe);
        whiskeyModel.setQuantityWhisky(quantity);
        whiskeyModel.setPrice(price);
        repository.save(whiskeyModel);
    }

}
