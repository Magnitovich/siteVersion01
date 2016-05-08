package com.example.dao;

import com.example.model.CarsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends CrudRepository<CarsModel, String> {

    @Query(value = "select cs from CarsModel cs where name_Cars=?")
    public List<CarsModel> findByNameCars(String nameCars);

    @Query(value = "select cs from CarsModel cs")
    public List<CarsModel> findAllCars();


}
