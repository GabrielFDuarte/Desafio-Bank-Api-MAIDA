/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

/**
 *
 * @author Gabriel Duarte
 */
public class JwtResponse {
    
    private String name;
    private String email;
    private String token;

    public JwtResponse(String name, String email, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
    
}
