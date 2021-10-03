/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gabriel Duarte
 */
public class JwtRequest {

    @NotNull(message = "Campo email ausente.")
    @NotEmpty(message = "Campo email vazio.")
    private String email;
    
    @NotNull(message = "Campo password ausente.")
    @NotEmpty(message = "Campo password vazio.")
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
