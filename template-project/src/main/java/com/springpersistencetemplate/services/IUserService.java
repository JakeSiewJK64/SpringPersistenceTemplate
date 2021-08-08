package com.springpersistencetemplate.services;

import java.util.List;

import com.springpersistencetemplate.models.UserModel;

public interface IUserService {
    List<UserModel> findAll();
}
