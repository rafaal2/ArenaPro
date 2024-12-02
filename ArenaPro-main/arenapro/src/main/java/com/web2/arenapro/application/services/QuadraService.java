package com.web2.arenapro.application.services;

import com.web2.arenapro.application.services.exceptions.DatabaseException;
import com.web2.arenapro.application.services.exceptions.ResourceNotFoundException;
import com.web2.arenapro.domain.dto.QuadraDTO;
import com.web2.arenapro.domain.entities.Quadra;
import com.web2.arenapro.domain.enums.TiposQuadra;
import com.web2.arenapro.domain.repositories.QuadraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuadraService {

    @Autowired
    private QuadraRepository quadraRepository;

    @Transactional(readOnly = true)
    public QuadraDTO findById(Long id) {
        Quadra quadra = quadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        return new QuadraDTO(quadra);
    }

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
        entity.setTipo(TiposQuadra.valueOf(dto.getTipo().toString()));
        entity.setCapacidade(dto.getCapacidade());
        entity.setStatus(dto.getStatus());
    }
}
