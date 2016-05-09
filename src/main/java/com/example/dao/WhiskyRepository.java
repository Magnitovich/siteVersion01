package com.example.dao;

import com.example.model.WhiskeyModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiskyRepository extends CrudRepository<WhiskeyModel, String> {

//    @Query(value = "select wm from WhiskeyModel ws where name_Whisky=?")
    public List<WhiskeyModel> findByNameWhisky(String name);
}
