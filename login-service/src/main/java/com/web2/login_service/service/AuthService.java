package com.web2.login_service.service;

import com.web2.login_service.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class AuthService {
    @Value("${jwt.secret}")
    private String secret;
    private static final long EXPIRATION_TIME = 86400000;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Key getSigningKey() {
        Key signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        System.out.println("Chave secreta (Base64 - login-service): " +
                java.util.Base64.getEncoder().encodeToString(signingKey.getEncoded()));

        return signingKey;

    }

    public String authenticate(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha))
                .map(usuario -> generateToken(email, usuario.getRole()))
                .orElse(null);
    }

    private String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }
}
