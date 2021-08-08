package com.springpersistencetemplate.controllers;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;
import com.springpersistencetemplate.models.AuthenticationRequest;
import com.springpersistencetemplate.models.AuthenticationResponse;
import com.springpersistencetemplate.models.UserModel;
import com.springpersistencetemplate.services.MyUserDetailsService;
import com.springpersistencetemplate.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * ? QUERIES USER DETAILS FROM MYUSERDETAILSSERVICE
         */
        final UserModel userModel = myUserDetailsService.loadUserModelByUsername(authenticationRequest.getUsername());

        // #region
        // !INCORRECT PASSWORD HANDING
        if (!userModel.getPasswordHash().equals(authenticationRequest.getPassword())) {
            throw new Exception("Invalid Password!");
        }
        // #endregion

        /**
         * ? GENERATES THE JWT TOKEN BASED ON THE USERDETAILS
         */
        final String jwt = jwtUtil.generateToken(userModel);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
