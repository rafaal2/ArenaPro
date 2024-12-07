package com.web2.login_service.service;

import com.web2.login_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authenticate(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> usuario.getSenha().equals(senha))
                .orElse(false);
    }
}
