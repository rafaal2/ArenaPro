package com.web2.arenapro.application.controllers;

import com.web2.arenapro.application.services.UsuarioService;
import com.web2.arenapro.domain.dtos.LoginRequest;
import com.web2.arenapro.domain.dtos.UsuarioDTO;
import com.web2.arenapro.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    private static Long usuarioLogado;
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {

        UsuarioDTO usuarioDTO = service.findById(id);
        return ResponseEntity.status(200).body(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = service.save(usuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = service.update(id, usuarioDTO);
        return ResponseEntity.status(200).body(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = service.autenticarUsuario(loginRequest.getEmail(), loginRequest.getSenha());

        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @GetMapping(value = "/usuario-logado")
    public ResponseEntity<Long> getUsuarioLogado() {
        Long usuarioLogadoId = service.getUsuarioLogado();
        return ResponseEntity.ok(usuarioLogadoId);
    }
}
