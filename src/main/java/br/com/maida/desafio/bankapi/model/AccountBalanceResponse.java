/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

import java.math.BigDecimal;

/**
 *
 * @author Gabriel Duarte
 */
public class AccountBalanceResponse {
    
    private String account_number;
    private BigDecimal balance;

    public AccountBalanceResponse(String account_number, BigDecimal balance) {
        this.account_number = account_number;
        this.balance = balance;
    }

    public String getAccount_number() {
        return account_number;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    
}
