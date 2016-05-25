package com.example.dao;


import com.example.model.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsersModel, String>{

    //поиск по праймери кей спринт делает автоматом



}
