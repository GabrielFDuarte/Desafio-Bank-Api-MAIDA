/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Duarte
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AuthService authService;
    
    @Override
    public UserDetails loadUserByUsername(String emailAuth) throws UsernameNotFoundException {
        
        br.com.maida.desafio.bankapi.model.User userAuth = authService.getUserByEmail(emailAuth);
        
        if (userAuth != null) {
            return new User(userAuth.getEmail(), userAuth.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + emailAuth);
        }
    }

}
