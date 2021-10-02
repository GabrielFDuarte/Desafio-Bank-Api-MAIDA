/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maida.desafio.bankapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gabriel Duarte
 */
@Component
public class JwtTokenUtil {

//    Considera o JWT válido por 5 minutos
    public static final long JWT_VALIDITY = 5 * 60;

    @Value("${jwt.secret}")
    private String secret;

//    Recupera o email do token jwt 
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

//    Retorna a data de expiração do token jwt 
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

//    Verifica se o token está expirado
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    Gera o token para o usuário
    public String generateToken(String emailAuth) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, emailAuth);
    }

//    Inicializa o token e define seu tempo de expiração
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

//    Valida o token
    public Boolean validateToken(String token, String email) {
        final String emailToken = getEmailFromToken(token);
        return (emailToken.equals(email) && !isTokenExpired(token));
    }

}
