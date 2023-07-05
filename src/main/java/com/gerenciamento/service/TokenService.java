//package com.gerenciamento.service;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.gerenciamento.model.UsuarioAuth;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.Date;
//
//@Service
//public class TokenService {
//    public String gerarToken(UsuarioAuth usuario) {
//        return JWT.create()
//                .withIssuer("Admin")
//                .withSubject(usuario.getUsername())
//                .withClaim("id", usuario.getId())
//                .withExpiresAt(Date.from(LocalDateTime.now()
//                        .plusMinutes(30)
//                        .toInstant(ZoneOffset.of("-03:00")))
//                ).sign(Algorithm.HMAC256("secreta"));
//    }
//
//
//    public String getSubject(String token) {
//        return JWT.require(Algorithm.HMAC256("secreta"))
//                .withIssuer("Admin")
//                .build().verify(token).getSubject();
//
//    }
//}
