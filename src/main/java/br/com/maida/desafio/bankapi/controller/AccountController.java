/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.controller;

import br.com.maida.desafio.bankapi.model.Account;
import br.com.maida.desafio.bankapi.model.AccountResponse;
import br.com.maida.desafio.bankapi.model.User;
import br.com.maida.desafio.bankapi.service.AccountService;
import br.com.maida.desafio.bankapi.service.AuthService;
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

}
