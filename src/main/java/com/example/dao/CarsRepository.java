package com.example.dao;

import com.example.model.CarsModel;
import com.example.model.YachtsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends CrudRepository<CarsModel, String> {

    @Query(value = "select cs from CarsModel cs where name=?")
    public List<CarsModel> findByNameCars(String name);

    @Query(value = "select cs from CarsModel cs")
    public List<CarsModel> findAllCars();

//    @Query(value = "select * from CarsModel cs where photo=?  name_Cars=?)")
    List<CarsModel> findByPhotoAndName(String photo, String name);

    CarsModel findFirstByName(String name);






}
