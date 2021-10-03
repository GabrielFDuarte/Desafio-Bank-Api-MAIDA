/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.model;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gabriel Duarte
 */
public class AccountTransfer {
    
    @NotNull(message = "Campo source_account_number ausente.")
    @NotEmpty(message = "Campo source_account_number vazio.")
    private String source_account_number;
    
    @NotNull(message = "Campo destination_account_number ausente.")
    @NotEmpty(message = "Campo destination_account_number vazio.")
    private String destination_account_number;
    
    @NotNull(message = "Campo amount ausente.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero.")
    @Digits(integer=3, fraction=2)
    private BigDecimal amount;
    
    public AccountTransfer() {
        
    }

    public AccountTransfer(String source_account_number, String destination_account_number, BigDecimal amount) {
        this.source_account_number = source_account_number;
        this.destination_account_number = destination_account_number;
        this.amount = amount;
    }

    public String getSource_account_number() {
        return source_account_number;
    }

    public void setSource_account_number(String source_account_number) {
        this.source_account_number = source_account_number;
    }

    public String getDestination_account_number() {
        return destination_account_number;
    }

    public void setDestination_account_number(String destination_account_number) {
        this.destination_account_number = destination_account_number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
