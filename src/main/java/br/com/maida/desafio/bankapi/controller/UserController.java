/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.controller;

import br.com.maida.desafio.bankapi.model.User;
import br.com.maida.desafio.bankapi.model.UserResponse;
import br.com.maida.desafio.bankapi.service.UserService;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> add(@Valid @RequestBody User jsonUserString) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (userService.validateEmailExists(jsonUserString.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).headers(httpHeaders).body(
                    new JSONObject()
                            .put("erro", "Já existe um usuário com o email informado.")
                            .toString());
        }

        String rawPassword = jsonUserString.getPassword();

        String encryptedPassword = userService.encryptUserPassword(rawPassword);
        jsonUserString.setPassword(encryptedPassword);

        userService.add(jsonUserString);

        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(jsonUserString.getEmail(), jsonUserString.getName()));

    }

}
