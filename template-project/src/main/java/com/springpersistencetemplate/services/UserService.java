package com.springpersistencetemplate.services;

import java.util.List;

import com.springpersistencetemplate.models.UserModel;
import com.springpersistencetemplate.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return (List<UserModel>) this.userRepository.findAll();
    }
}
