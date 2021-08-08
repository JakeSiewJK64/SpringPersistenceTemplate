package com.springpersistencetemplate.repositories;

import com.springpersistencetemplate.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public UserModel getUserByUsername(String username);
}
