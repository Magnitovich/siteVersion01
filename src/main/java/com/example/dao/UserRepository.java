package com.example.dao;


import com.example.model.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UsersModel, String>{

    //поиск по праймери кей спринт делает автоматом

    @Query(value = "select  cs from UsersModel cs WHERE name=?")
    List<UsersModel> findByName(String name);

    @Query(value = "select  cs from UsersModel cs WHERE email=?")
    List<UsersModel> findByEmail(String email);

//    @Query(value = "select NAME from USERS", nativeQuery = true )
//    List<String> findByName();
}
