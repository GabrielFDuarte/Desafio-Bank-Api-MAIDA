/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gabriel Duarte
 */
public class Account {
    
    @NotNull(message = "Campo number ausente.")
    @NotEmpty(message = "Campo number vazio.")
    private String number;
    
    @NotNull(message = "Campo balance ausente.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Saldo deve ser maior ou igual a zero.")
    private BigDecimal balance;
    
    private String userEmail;
    
    private String userName;
    
    public Account() {
        
    }

    public Account(String number, BigDecimal balance, String userEmail, String userName) {
        this.number = number;
        this.balance = balance;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
