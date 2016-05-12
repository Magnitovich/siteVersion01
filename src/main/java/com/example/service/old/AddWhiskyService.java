package com.example.service.old;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
}
