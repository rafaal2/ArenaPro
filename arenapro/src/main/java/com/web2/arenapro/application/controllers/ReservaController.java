package com.web2.arenapro.application.controllers;

import com.web2.arenapro.application.services.ReservaService;
import com.web2.arenapro.application.services.UsuarioService;
import com.web2.arenapro.domain.dtos.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        ReservaDTO reservaDTO = service.findById(id);
        return ResponseEntity.ok(reservaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll(@RequestParam(required = false) Long usuarioId) {
        List<ReservaDTO> reservaDTO;
        if (usuarioId != null) {
            reservaDTO = service.findByUsuarioId(usuarioId);
        } else {
            reservaDTO = service.findAll();
        }
        return ResponseEntity.ok(reservaDTO);
    }


    @PostMapping
    public ResponseEntity<ReservaDTO> save(@RequestBody ReservaDTO reservaDTO) {
        reservaDTO.setUsuarioId(usuarioService.getUsuarioLogado());
        reservaDTO = service.save(reservaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(reservaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(reservaDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        reservaDTO = service.update(id, reservaDTO);
        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
