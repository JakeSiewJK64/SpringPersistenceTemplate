package com.springpersistencetemplate.services;

import java.util.ArrayList;

import com.springpersistencetemplate.models.UserModel;
import com.springpersistencetemplate.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user;

        user = this.userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " does not exist");
        }
        // return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        return new User(user.getUsername(), user.getPasswordHash(), new ArrayList<>());
    }

    public UserModel loadUserModelByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " does not exist");
        }
        return user;
    }
}
