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
public class AccountTransferResponse {
    
    private BigDecimal amount;
    private String source_account_number;
    private String destination_account_number;
    private JSONObject user_transfer;

    public AccountTransferResponse(BigDecimal amount, String source_account_number, String destination_account_number, String email, String name) {
        this.amount = amount;
        this.source_account_number = source_account_number;
        this.destination_account_number = destination_account_number;
        
        JSONObject jsonUserTransferAccount = new JSONObject();
        
        jsonUserTransferAccount.put("email", email);
        jsonUserTransferAccount.put("name", name);
        
        this.user_transfer = jsonUserTransferAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSource_account_number() {
        return source_account_number;
    }

    public String getDestination_account_number() {
        return destination_account_number;
    }

    public JSONObject getUser_transfer() {
        return user_transfer;
    }
    
}
