package com.example.dao;

import com.example.model.YachtsModel;
import org.springframework.context.Lifecycle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YachtRepository extends CrudRepository<YachtsModel, String>{

    @Query(value = "select ys from YachtsModel ys where name=?")
    List<YachtsModel> findByNameYacht(String name);

    @Query(value = "select ys from YachtsModel ys")
    List<YachtsModel> findAllYachts();


    List<YachtsModel> findByNameAndPhoto(String photo, String name);

}
