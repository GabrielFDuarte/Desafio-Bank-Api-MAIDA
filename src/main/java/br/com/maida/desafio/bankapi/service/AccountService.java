/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.service;

import br.com.maida.desafio.bankapi.model.Account;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Duarte
 */
@Service
public class AccountService {

    private List<Account> accounts;

//    Cria a lista de contas bancárias caso ainda não tenha sido criada
    public void createAccountList() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
    }

//    Valida se o número de conta enviado para cadastro já existe entre as contas.
    public boolean validateNumberExists(String number) {
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getNumber().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

//    Valida se a conta é do usuário.
    public boolean validateAccountEmail(String number, String email) {
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getNumber().equals(number)) {
                    return account.getUserEmail().equals(email);
                }
            }
        }
        return false;
    }

//    Adição do objeto Account na list de contas cadastradas.
    public void add(Account account) {
        createAccountList();
        accounts.add(account);
    }
    
//    Retorna o objeto Account buscado pelo número
    public Account getAccountByNumber(String number) {
        if (accounts == null) {
            return null;
        }

        for (Account account : accounts) {
            if (account.getNumber().equals(number)) {
                return account;
            }
        }
        return null;
    }

}
