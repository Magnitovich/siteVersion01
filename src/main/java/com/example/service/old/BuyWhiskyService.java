package com.example.service.old;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyWhiskyService {

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
//        WhiskeyModel model = list.get(0);

        int quantityInDB = model.getQuantityWhisky();
        if(quantityInDB==0) {
            System.out.println("In DB ZERO");
        } else {

            int result = quantityInDB - quantityFromUI;
            model.setQuantityWhisky(result);
            repository.save(model);

        }

    }

    public void seeAllWhisky() {

        repository.findAll();
    }

}
