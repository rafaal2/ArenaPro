package com.web2.arenapro.application.controllers;

import com.web2.arenapro.application.services.HorariosService;
import com.web2.arenapro.domain.dto.HorariosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/horarios")
public class HorariosController {

    @Autowired
    private HorariosService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<HorariosDTO> findById(@PathVariable Long id) {
        HorariosDTO horariosDTO = service.findById(id);
        return ResponseEntity.ok(horariosDTO);
    }

    @PostMapping
    public ResponseEntity<HorariosDTO> save(@RequestBody HorariosDTO horariosDTO) {
        horariosDTO = service.save(horariosDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(horariosDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(horariosDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HorariosDTO> update(@PathVariable Long id, @RequestBody HorariosDTO horariosDTO) {
        horariosDTO = service.update(id, horariosDTO);
        return ResponseEntity.ok(horariosDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
