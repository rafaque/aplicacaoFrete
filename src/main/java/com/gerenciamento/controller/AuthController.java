//package com.gerenciamento.controller;
//
//import com.gerenciamento.dro.Login;
//import com.gerenciamento.model.UsuarioAuth;
//import com.gerenciamento.service.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @PostMapping("/auth/login")
//    public String login(@RequestBody Login login) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(login.login(),
//                        login.password());
//
//        Authentication authenticate = this.authenticationManager
//                .authenticate(usernamePasswordAuthenticationToken);
//
//        var usuario = (UsuarioAuth) authenticate.getPrincipal();
//
//        return tokenService.gerarToken(usuario);
//
//    }
//}
