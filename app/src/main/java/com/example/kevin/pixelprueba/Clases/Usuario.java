package com.example.kevin.pixelprueba.Clases;

/**
 * Created by Jon on 26/01/2018.
 */

public class Usuario {

    String username, password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
