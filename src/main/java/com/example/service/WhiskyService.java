package com.example.service;

import com.example.dao.WhiskyRepository;
import com.example.model.WhiskeyModel;
import com.example.model.WhiskyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WhiskyService {

    @Autowired
    private WhiskyRepository repository;

    public WhiskyDTO convertModelToDTO(WhiskeyModel model) {
        WhiskyDTO whiskyDTO = new WhiskyDTO();
        whiskyDTO.setId(model.getId());
        whiskyDTO.setPhoto(model.getPhoto());
        whiskyDTO.setNameWhisky(model.getNameWhisky());
        whiskyDTO.setDescribeWhisky(model.getDescribeWhisky());
        whiskyDTO.setQuantityWhisky(model.getQuantityWhisky());
        whiskyDTO.setPrice(model.getPrice());

        return whiskyDTO;
    }
    public List<WhiskyDTO> convertListModelToListDTO(List<WhiskeyModel> whiskeyModels) {
        List<WhiskyDTO> whisky = new ArrayList<>();
        for (WhiskeyModel model:whiskeyModels) {
            WhiskyDTO dto = convertModelToDTO(model);
            whisky.add(dto);
        }
        return whisky;
    }

    public String convertIdToName(Long id) {

        WhiskeyModel model = repository.findOne(id);
        WhiskyDTO dto = convertModelToDTO(model);
        return dto.getNameWhisky();

    }

    public List<WhiskyDTO> viewSelectedWhisky(String name) {
        List<WhiskeyModel> byNameWhisky = repository.findByNameWhisky(name);
        List<WhiskyDTO> whiskyDTOs = convertListModelToListDTO(byNameWhisky);
        return whiskyDTOs;
    }

    @Transactional
    public void changeInfoInDB(final String name, final Integer quantityFromUI) {

        List<WhiskeyModel> list = repository.findByNameWhisky(name);
        WhiskeyModel model = list.get(0);
        System.out.println("********************************");
        System.out.println("name+Q"+name+" "+quantityFromUI);
        int quantityInDB = model.getQuantityWhisky();
        if(quantityInDB==0) {
            System.out.println("In DB ZERO");
        } else {

            int result = quantityInDB - quantityFromUI;
            model.setQuantityWhisky(result);
            repository.save(model);
        }
    }

    public List<WhiskyDTO> seeAllWhisky() {

        List<WhiskeyModel> all = repository.findAllWhisky();
        List<WhiskyDTO> whiskyDTOs = convertListModelToListDTO(all);
        return whiskyDTOs;
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

    public WhiskyDTO viewByNameWhisky(Long idWhyski) {
        WhiskeyModel whiskeyModels = repository.findOne(idWhyski);
        WhiskyDTO whiskyDTOs = convertModelToDTO(whiskeyModels);
        return whiskyDTOs;
    }
    @Transactional
    public void editWhisky(WhiskyDTO whiskyDTO) {

        WhiskeyModel whiskeyModels = repository.findOne(whiskyDTO.getId());

        if(whiskyDTO.getPhoto() != null) {
            whiskeyModels.setPhoto(whiskyDTO.getPhoto());
        }
        whiskeyModels.setNameWhisky(whiskyDTO.getNameWhisky());
        whiskeyModels.setDescribeWhisky(whiskyDTO.getDescribeWhisky());
        whiskeyModels.setQuantityWhisky(whiskyDTO.getQuantityWhisky());
        whiskeyModels.setPrice(whiskyDTO.getPrice());

        repository.save(whiskeyModels);

    }

    public void delete(List<Long> nameWhisky) {

        for(Long name:nameWhisky){

            repository.delete(name);
        }
    }
    public List<WhiskyDTO> viewPhoto(String photo) {

       List<WhiskeyModel> models = repository.findByPhoto(photo);
        List<WhiskyDTO> whiskyDTOs = convertListModelToListDTO(models);
        return whiskyDTOs;
    }
    public List<WhiskyDTO> viewName(String name) {

       List<WhiskeyModel> models = repository.findByNameWhisky(name);
        List<WhiskyDTO> whiskyDTOs = convertListModelToListDTO(models);
        return whiskyDTOs;
    }
}
