package com.example.service;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WhiskyService {

    @Autowired
    private WhiskyRepository repository;

    public List<WhiskeyModel> viewSelectedWhisky(String name) {
        List<WhiskeyModel> byNameWhisky = repository.findByNameWhisky(name);
        return byNameWhisky;
    }

    @Transactional
    public void changeInfoInDB(final String name, final Integer quantityFromUI) {

        List<WhiskeyModel> list = repository.findByNameWhisky(name);
        WhiskeyModel model = list.get(0);

        int quantityInDB = model.getQuantityWhisky();
        if(quantityInDB==0) {
            System.out.println("In DB ZERO");
        } else {

            int result = quantityInDB - quantityFromUI;
            model.setQuantityWhisky(result);
            repository.save(model);
        }
    }

    public Iterable<WhiskeyModel> seeAllWhisky() {

        Iterable<WhiskeyModel> all = repository.findAll();
        return all;
    }

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

    public WhiskeyModel editWhisky(final String nameWhisky) {

        List<WhiskeyModel> byNameWhisky = repository.findByNameWhisky(nameWhisky);
        WhiskeyModel model = byNameWhisky.get(0);
        model.getPhoto();
        model.getNameWhisky();
        model.getDescribeWhisky();
        model.getQuantityWhisky();
        model.getPrice();
        return model;
    }

    public void delete(List<String> nameWhisky) {

        for(String name:nameWhisky){

            repository.delete(name);
        }
    }
}
