package com.springpersistencetemplate.models;

public class AuthenticationResponse {
    private String jwt;

    public String getJwt() {
        return this.jwt;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
