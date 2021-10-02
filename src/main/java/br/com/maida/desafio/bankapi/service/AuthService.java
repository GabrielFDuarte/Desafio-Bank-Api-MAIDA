/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.service;

import br.com.maida.desafio.bankapi.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Duarte
 */
@Service
public class AuthService {

    @Autowired
    private UserService userService;

//    Retorna o objeto User buscado pelo email
    public User getUserByEmail(String emailAuth) {
        List<User> listUser = userService.listUsers();
        if (listUser == null) {
            return null;
        }

        for (User user : listUser) {
            if (user.getEmail().equals(emailAuth)) {
                return user;
            }
        }
        return null;
    }
    
//    Retorna o nome do usuário buscado pelo email
    public String getUserNameByEmail(String emailAuth) {
        List<User> listUser = userService.listUsers();
        if (listUser == null) {
            return null;
        }

        for (User user : listUser) {
            if (user.getEmail().equals(emailAuth)) {
                return user.getName();
            }
        }
        return null;
    }

}
