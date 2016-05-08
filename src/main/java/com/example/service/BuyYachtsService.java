package com.example.service;

import com.example.dao.YachtRepository;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyYachtsService {

    @Autowired
    private YachtRepository yachtRepository;

    @Transactional
    public void buyAndUpdate(String name, Integer quantityUI) {

        List<YachtsModel> listYacht = yachtRepository.findByNameYacht(name);
        if(listYacht.size()==0) {
            System.out.println("EXCEPTION YACHT NOT FOUND");
        } else {
            YachtsModel model = listYacht.get(0);
            int quantityYachtInDB = model.getNumber();
            int result = quantityYachtInDB-quantityUI;
            model.setNumber(result);
            yachtRepository.save(model);
        }

    }
}
