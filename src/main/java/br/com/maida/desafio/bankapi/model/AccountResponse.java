/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

import java.math.BigDecimal;
import org.json.JSONObject;

/**
 *
 * @author Gabriel Duarte
 */
public class AccountResponse {

    private String number;
    private BigDecimal balance;
    private JSONObject user;

    public AccountResponse(String number, BigDecimal balance, String email, String name) {
        this.number = number;
        this.balance = balance;
        
        JSONObject jsonUserAccount = new JSONObject();
        
        jsonUserAccount.put("email", email);
        jsonUserAccount.put("name", name);
        
        this.user = jsonUserAccount;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public JSONObject getUser() {
        return user;
    }
    
}
