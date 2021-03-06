package com.example.dao;

import com.example.model.WhiskeyModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiskyRepository extends CrudRepository<WhiskeyModel, Long> {

//    @Query(value = "select wm from WhiskeyModel ws where name_Whisky=?")
    public List<WhiskeyModel> findByNameWhisky(String name);

    List<WhiskeyModel> findByPhoto(String photo);

    @Query(value = "SELECT ws FROM WhiskeyModel ws")
    List<WhiskeyModel> findAllWhisky();


    WhiskeyModel findFirstByNameWhisky(String name);


}
