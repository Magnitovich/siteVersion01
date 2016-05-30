package com.example.dao;

import com.example.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepositiry extends CrudRepository<UserRole, String> {




}
