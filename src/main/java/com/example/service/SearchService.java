package com.example.service;

import com.example.dao.CarsRepository;
import com.example.dao.WhiskyRepository;
import com.example.dao.YachtRepository;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    
    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    WhiskyService whiskyService;
    
    @Autowired
    YachtRepository yachtRepository;
    @Autowired
    YachtsService yachtService;

    @Autowired
    CarsRepository carsRepository;
    @Autowired
    CarsService carsService;

    List<WhiskyDTO> answer = new ArrayList<>();
    List<YachtDTO> answerYacht = new ArrayList<>();
    List<CarsDTO> answerCar = new ArrayList<>();


//    public List<WhiskyDTO> serchInWhisky(String search) {
//
////        List<WhiskeyModel> byNameWhisky = whiskyRepository.findByNameWhisky(search);
////        List<WhiskyDTO> whiskyDTOs = whiskyService.convertListModelToListDTO(byNameWhisky);
////
////        return whiskyDTOs;
//        answer.clear();
//        List<WhiskeyModel> allWhisky = whiskyRepository.findAllWhisky();
//        List<WhiskyDTO> whiskyDTOs = whiskyService.convertListModelToListDTO(allWhisky);
//        List<WhiskyDTO> searchWhisky = new ArrayList<>();
//        searchWhisky.addAll(whiskyDTOs);
//        for (WhiskyDTO w :searchWhisky) {
//            if (w.getNameWhisky().contains(search)) {
//                answer.add(w);
//            }
//        }
//        return answer;

        public List<WhiskyDTO> searchInWhisky(String search) {

            answer.clear();
            List<WhiskeyModel> allWhisky = whiskyRepository.findAllWhisky();
            List<WhiskyDTO> whiskyDTOs = whiskyService.convertListModelToListDTO(allWhisky);
            List<WhiskyDTO> searchWhisky = new ArrayList<>();
            searchWhisky.addAll(whiskyDTOs);
            for (WhiskyDTO w : searchWhisky) {
                if (w.getNameWhisky().toLowerCase().contains(search) ||w.getNameWhisky().toUpperCase().contains(search)) {
                    answer.add(w);
                }
            }
            return answer;
        }
    public List<YachtDTO> searchInYachts(String searchYacht)  {
        answerYacht.clear();
        List<YachtsModel> yachtModel = yachtRepository.findAllYachts();
        List<YachtDTO> yDTO = yachtService.convertListModelToListDTO(yachtModel);
        List<YachtDTO> searchYachts = new ArrayList<>();
        searchYachts.addAll(yDTO);
        for (YachtDTO y:searchYachts) {
            if(y.getName().toLowerCase().contains(searchYacht) ||y.getName().toUpperCase().contains(searchYacht)) {
                answerYacht.add(y);
            }
        }

        return answerYacht;
    }
    public List<CarsDTO> searchInCar(String search) {

        answerCar.clear();
        List<CarsModel> allCar = carsRepository.findAllCars();
        List<CarsDTO> carsDTOs = carsService.convertListModelToLIstDTO(allCar);
        List<CarsDTO> searchCar = new ArrayList<>();
        searchCar.addAll(carsDTOs);
        for (CarsDTO w : searchCar) {
            if (w.getName().toLowerCase().contains(search) ||w.getName().toUpperCase().contains(search)) {
                answerCar.add(w);
            }
        }
        return answerCar;
    }
}
