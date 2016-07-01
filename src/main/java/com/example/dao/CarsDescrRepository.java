package com.example.dao;

import com.example.model.CarDescrModel;
import com.example.model.CarsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsDescrRepository extends CrudRepository<CarDescrModel, Long> {
}
