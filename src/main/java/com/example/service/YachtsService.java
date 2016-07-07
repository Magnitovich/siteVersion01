package com.example.service;

import com.example.dao.YachtRepository;
import com.example.model.CarsDTO;
import com.example.model.YachtDTO;
import com.example.model.YachtsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class YachtsService {

    @Autowired
    private YachtRepository yachtRepository;

    public List<YachtDTO> vewAllYachts() {

        List<YachtsModel> allYachts = yachtRepository.findAllYachts();
        List<YachtDTO> yachtDTOs = convertListModelToListDTO(allYachts);
        return yachtDTOs;
    }

    private List<YachtDTO> convertListModelToListDTO(List<YachtsModel> yachtsModels) {

        List<YachtDTO> yachtDTOs = new ArrayList<>();
        for (YachtsModel add:yachtsModels) {
            YachtDTO yachtDTO = convertModelToDTO(add);
            yachtDTOs.add(yachtDTO);
        }
    return yachtDTOs;
    }


    public void deleteYachts(List<Long> nameYachts) {

        for (Long yacht:nameYachts) {
            yachtRepository.delete(yacht);
        }
    }
    public List<YachtDTO> viewSelectedYacht(String name) {

        List<YachtsModel> byNameYacht = yachtRepository.findByNameYacht(name);
        List<YachtDTO> yachtDTOs = convertListModelToListDTO(byNameYacht);
        return yachtDTOs;
    }

    public String convertIdToName(Long id) {

        YachtsModel model = yachtRepository.findOne(id);
        YachtDTO yachtDTO = convertModelToDTO(model);
        return yachtDTO.getName();
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

    @Transactional
    public void editYacht(YachtDTO yachtDTO) {

        System.out.println(yachtDTO.getPhoto()+" -Photo, Name - "+ yachtDTO.getName());

        YachtsModel model = yachtRepository.findOne(yachtDTO.getIdYacht());

        if (!StringUtils.isEmpty(yachtDTO.getPhoto())) {
            model.setPhoto(yachtDTO.getPhoto());
        }
        model.setName(yachtDTO.getName());
        model.setDescriptions(yachtDTO.getDescriptions());
        model.setNumber(yachtDTO.getNumber());
        model.setPrice(yachtDTO.getPrice());

        yachtRepository.save(model);
    }

    public YachtDTO viewSelecterYachtModel(Long idSelectedYachts) {
        YachtsModel byName = yachtRepository.findOne(idSelectedYachts);
        YachtDTO yachtDTO = convertModelToDTO(byName);
        return yachtDTO;

    }

    private YachtDTO convertModelToDTO(YachtsModel model) {
        YachtDTO yachtDTO = new YachtDTO();
        yachtDTO.setIdYacht(model.getIdYacht());
        yachtDTO.setPhoto(model.getPhoto());
        yachtDTO.setName(model.getName());
        yachtDTO.setDescriptions(model.getDescriptions());
        yachtDTO.setNumber(model.getNumber());
        yachtDTO.setPrice(model.getPrice());
        return yachtDTO;
    }

    public List<YachtDTO> viewPhoto(String photo) {
        List<YachtsModel> yachtsModels = yachtRepository.findByPhoto(photo);
        List<YachtDTO> yachtDTOs = convertListModelToListDTO(yachtsModels);
        return yachtDTOs;
    }
    public List<YachtDTO> viewName(String name) {
        List<YachtsModel> yachtsModels = yachtRepository.findByNameYacht(name);
        List<YachtDTO> yachtDTOs = convertListModelToListDTO(yachtsModels);
        return yachtDTOs;
    }

}
