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
public class AccountBalance {

    @NotNull(message = "Campo account_number ausente.")
    @NotEmpty(message = "Campo account_number vazio.")
    private String account_number;

    public AccountBalance() {

    }

    public AccountBalance(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
    
}
