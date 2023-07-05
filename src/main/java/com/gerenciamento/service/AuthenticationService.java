//package com.gerenciamento.service;
//
//import com.gerenciamento.repository.UsuarioAuthRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioAuthRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        return usuarioRepository.findByLogin(username);
//    }
//}