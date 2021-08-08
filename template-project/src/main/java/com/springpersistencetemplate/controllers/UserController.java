package com.springpersistencetemplate.controllers;

import java.util.List;

import com.springpersistencetemplate.models.UserModel;
import com.springpersistencetemplate.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/getAllUsers")
    public List<UserModel> getAllUsers() {
        return this.userService.findAll();
    }
}
