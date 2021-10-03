/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.controller;

import br.com.maida.desafio.bankapi.model.Account;
import br.com.maida.desafio.bankapi.model.AccountResponse;
import br.com.maida.desafio.bankapi.model.AccountTransfer;
import br.com.maida.desafio.bankapi.model.AccountTransferResponse;
import br.com.maida.desafio.bankapi.model.User;
import br.com.maida.desafio.bankapi.service.AccountService;
import br.com.maida.desafio.bankapi.service.AuthService;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gabriel Duarte
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthService authService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addAccount(@Valid @RequestBody Account jsonAccountString) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (accountService.validateNumberExists(jsonAccountString.getNumber())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).headers(httpHeaders).body(
                    new JSONObject()
                            .put("erro", "Já existe uma conta com o número informado")
                            .toString());
        }

        User tempUser = authService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).headers(httpHeaders).body(
                    new JSONObject()
                            .put("message", "Acesso negado.")
                            .toString());
        }

        jsonAccountString.setUserEmail(tempUser.getEmail());
        jsonAccountString.setUserName(tempUser.getName());

        accountService.add(jsonAccountString);

        AccountResponse accountResponse = new AccountResponse(
                jsonAccountString.getNumber(),
                jsonAccountString.getBalance(),
                jsonAccountString.getUserEmail(),
                jsonAccountString.getUserName());

        JSONObject jsonAccountReturn = new JSONObject();
        jsonAccountReturn.put("number", jsonAccountString.getNumber());
        jsonAccountReturn.put("balance", jsonAccountString.getBalance());
        jsonAccountReturn.put("user", accountResponse.getUser());

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(jsonAccountReturn.toString());
    }

    @PostMapping(path = "/transfer", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> transfer(@Valid @RequestBody AccountTransfer jsonAccountTransferString) {

        if (accountService.validateNumberExists(jsonAccountTransferString.getSource_account_number())) {
            if (accountService.validateAccountEmail(jsonAccountTransferString.getSource_account_number(),
                    SecurityContextHolder.getContext().getAuthentication().getName())) {

                if (!accountService.validateNumberExists(jsonAccountTransferString.getDestination_account_number())) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                            new JSONObject()
                                    .put("erro", "Conta destino não encontrada")
                                    .toString());
                }

                Account sourceAccount = accountService.getAccountByNumber(jsonAccountTransferString.getSource_account_number());

                if (sourceAccount.getBalance().compareTo(jsonAccountTransferString.getAmount()) == -1) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                            new JSONObject()
                                    .put("erro", "Saldo insuficiente na conta de origem")
                                    .toString());
                }

                Account destinyAccount = accountService.getAccountByNumber(jsonAccountTransferString.getDestination_account_number());

                BigDecimal sourceAccountBalance = sourceAccount.getBalance();
                BigDecimal destinyAccountBalance = destinyAccount.getBalance();

                sourceAccount.setBalance(sourceAccountBalance.subtract(jsonAccountTransferString.getAmount()));
                destinyAccount.setBalance(destinyAccountBalance.add(jsonAccountTransferString.getAmount()));

                AccountTransferResponse accountTransferResponse = new AccountTransferResponse(
                        jsonAccountTransferString.getAmount(),
                        jsonAccountTransferString.getSource_account_number(),
                        jsonAccountTransferString.getDestination_account_number(),
                        sourceAccount.getUserEmail(),
                        sourceAccount.getUserName());

                JSONObject jsonAccountTransferReturn = new JSONObject();
                jsonAccountTransferReturn.put("amount", accountTransferResponse.getAmount());
                jsonAccountTransferReturn.put("source_account_number", accountTransferResponse.getSource_account_number());
                jsonAccountTransferReturn.put("destination_account_number", accountTransferResponse.getDestination_account_number());
                jsonAccountTransferReturn.put("user_transfer", accountTransferResponse.getUser_transfer());

                return ResponseEntity.status(HttpStatus.OK).body(jsonAccountTransferReturn.toString());

            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                        new JSONObject()
                                .put("erro", "Conta de origem não encontrada para o usuário informado")
                                .toString());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new JSONObject()
                            .put("erro", "Conta de origem não encontrada para o usuário informado")
                            .toString());
        }

    }

}
