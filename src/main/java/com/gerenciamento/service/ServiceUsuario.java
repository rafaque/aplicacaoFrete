package com.gerenciamento.service;

import com.gerenciamento.exception.CriptoExistsException;
import com.gerenciamento.exception.ServiceExc;
import com.gerenciamento.exception.UserExistsException;
import com.gerenciamento.model.Usuario;
import com.gerenciamento.repository.UsuarioRepository;
import com.gerenciamento.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario user) throws Exception {

        try {
            if(usuarioRepository.findByUser(user.getUser()) !=null) {
                throw new UserExistsException("Este usuario já esta cadastrado: " + user.getUser());
            }

            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error na criptografia da senha");
        }
        usuarioRepository.save(user);
    }

    public Usuario loginUser(String user, String senha) throws ServiceExc {

        return usuarioRepository.buscarLogin(user, senha);
    }
}
