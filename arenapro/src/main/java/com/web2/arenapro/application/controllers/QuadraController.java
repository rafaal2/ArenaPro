package com.web2.arenapro.application.controllers;

import com.web2.arenapro.application.services.QuadraService;
import com.web2.arenapro.domain.dtos.QuadraDTO;
import com.web2.arenapro.domain.entities.Quadra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "file:///C:/Users/rafae/Downloads/front/quadras/index.html")
@RestController
@RequestMapping(value = "/quadras")
public class QuadraController {

    @Autowired
    private QuadraService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuadraDTO> findById(@PathVariable Long id) {
        QuadraDTO quadraDTO = service.findById(id);
        return ResponseEntity.ok(quadraDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Quadra> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<QuadraDTO> save(@RequestBody QuadraDTO quadraDTO) {
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
