package com.web2.arenapro.application.services;

import com.web2.arenapro.application.services.exceptions.DatabaseException;
import com.web2.arenapro.application.services.exceptions.ResourceNotFoundException;
import com.web2.arenapro.domain.dtos.QuadraDTO;
import com.web2.arenapro.domain.entities.Quadra;
import com.web2.arenapro.domain.enums.TiposQuadra;
import com.web2.arenapro.domain.repositories.QuadraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuadraService {

    @Autowired
    private QuadraRepository quadraRepository;

<<<<<<< HEAD
    @Transactional
    public List<Quadra> findAll(){
        return quadraRepository.findAll();
    }
=======
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945
    @Transactional(readOnly = true)
    public QuadraDTO findById(Long id) {
        Quadra quadra = quadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        return new QuadraDTO(quadra);
    }

<<<<<<< HEAD
=======
    @Transactional(readOnly = true)
    public QuadraDTO findByName(String name) {
        Quadra quadra = quadraRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("quadra não encontrado"));
        return new QuadraDTO(quadra);
    }

    @Transactional(readOnly = true)
    public List<QuadraDTO> findAll() {
        List<Quadra> quadras = quadraRepository.findAll();
        return quadras.stream()
                .map(quadra -> new QuadraDTO(quadra))
                .collect(Collectors.toList());
    }

>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945
    @Transactional
    public QuadraDTO save(QuadraDTO quadraDTO) {
        Quadra entity = new Quadra();
        copyDtoToEntity(quadraDTO, entity);
        entity = quadraRepository.save(entity);
        return new QuadraDTO(entity);
    }

    @Transactional
    public QuadraDTO update(Long id, QuadraDTO quadraDTO) {
        try {
            Quadra entity = quadraRepository.getReferenceById(id);
            copyDtoToEntity(quadraDTO, entity);
            entity = quadraRepository.save(entity);
            return new QuadraDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Quadra não encontrada: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!quadraRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
        try {
            quadraRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro no banco de dados ao tentar excluir a quadra");
        }
    }

    private void copyDtoToEntity(QuadraDTO dto, Quadra entity) {
        entity.setNome(dto.getNome());
        entity.setLocalizacao(dto.getLocalizacao());
<<<<<<< HEAD
        entity.setTipo(dto.getTipo());
=======
        entity.setTipo(TiposQuadra.valueOf(dto.getTipo().toString()));
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945
        entity.setCapacidade(dto.getCapacidade());
        entity.setStatus(dto.getStatus());
    }
}
