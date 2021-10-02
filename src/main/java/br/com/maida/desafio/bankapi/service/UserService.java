/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.service;

import br.com.maida.desafio.bankapi.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Duarte
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<User> users;

//    Cria a lista de usuários caso ainda não tenha sido criada.
    public void createUserList() {
        if (users == null) {
            users = new ArrayList<>();
        }
    }

//    Valida se o email enviado para cadastro já existe entre os usuários.
    public boolean validateEmailExists(String email) {
        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

//    Criptografa a senha do usuário
    public String encryptUserPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

//    Adição do objeto User na list de usuários cadastrados.
    public void add(User user) {
        createUserList();
        users.add(user);
    }

//    Retorna a lista de usuários.
    public List<User> listUsers() {
        return users;
    }

}
