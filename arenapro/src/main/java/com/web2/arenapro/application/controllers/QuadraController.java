package com.web2.arenapro.application.controllers;

import com.web2.arenapro.application.services.QuadraService;
import com.web2.arenapro.domain.dtos.QuadraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/quadras")
public class QuadraController {

    @Autowired
    private QuadraService service;

    @GetMapping
    public ResponseEntity<List<QuadraDTO>> findAll() {
        List<QuadraDTO> quadraDTO = service.findAll();
        return ResponseEntity.ok(quadraDTO);
    }

    @PostMapping
    public ResponseEntity<QuadraDTO> save(@RequestBody QuadraDTO quadraDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Usuário autenticado: " + auth.getPrincipal());
        System.out.println("Autoridades: " + auth.getAuthorities());
        String role = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403).build(); // Retornar 403 Forbidden se não for ADMIN
        }

        quadraDTO = service.save(quadraDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(quadraDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(quadraDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<QuadraDTO> update(@PathVariable Long id, @RequestBody QuadraDTO quadraDTO) {
        quadraDTO = service.update(id, quadraDTO);
        return ResponseEntity.ok(quadraDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
