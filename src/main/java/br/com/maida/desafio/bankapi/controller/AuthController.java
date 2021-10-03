/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.controller;

import br.com.maida.desafio.bankapi.config.JwtTokenUtil;
import br.com.maida.desafio.bankapi.model.JwtRequest;
import br.com.maida.desafio.bankapi.model.JwtResponse;
import br.com.maida.desafio.bankapi.service.AuthService;
import br.com.maida.desafio.bankapi.service.JwtUserDetailsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> authenticate(@Valid @RequestBody JwtRequest jsonAuthString) throws Exception {

        authenticate(jsonAuthString.getEmail(), jsonAuthString.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(jsonAuthString.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(authService.getUserNameByEmail(jsonAuthString.getEmail()), jsonAuthString.getEmail(), token));

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
