package com.web2.arenapro.application.services;

import com.web2.arenapro.application.services.exceptions.DatabaseException;
import com.web2.arenapro.application.services.exceptions.ResourceNotFoundException;
import com.web2.arenapro.domain.dto.HorariosDTO;
import com.web2.arenapro.domain.entities.Horarios;
import com.web2.arenapro.domain.entities.Quadra;
import com.web2.arenapro.domain.repositories.HorariosRepository;
import com.web2.arenapro.domain.repositories.QuadraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorariosService {

    @Autowired
    private HorariosRepository horariosRepository;

    @Autowired
    private QuadraRepository quadraRepository;

    @Transactional(readOnly = true)
    public HorariosDTO findById(Long id) {
        Horarios horarios = horariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado para o ID: " + id));
        return new HorariosDTO(horarios);
    }

    @Transactional
    public HorariosDTO save(HorariosDTO horariosDTO) {
        Horarios entity = new Horarios();
        copyDtoToEntity(horariosDTO, entity);
        entity = horariosRepository.save(entity);
        return new HorariosDTO(entity);
    }

    @Transactional
    public HorariosDTO update(Long id, HorariosDTO horariosDTO) {
        try {
            Horarios entity = horariosRepository.getReferenceById(id);
            copyDtoToEntity(horariosDTO, entity);
            entity = horariosRepository.save(entity);
            return new HorariosDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Horário não encontrado para o ID: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!horariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Horário não encontrado para o ID: " + id);
        }
        try {
            horariosRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro ao tentar excluir o horário. Verifique as dependências.");
        }
    }

    private void copyDtoToEntity(HorariosDTO dto, Horarios entity) {
        Quadra quadra = quadraRepository.findById(dto.getQuadraId())
                .orElseThrow(() -> new ResourceNotFoundException("Quadra não encontrada para o ID: " + dto.getQuadraId()));

        entity.setQuadra(quadra);
        entity.setDiaSemana(dto.getDiaSemana());
        entity.setHorarioInicio(dto.getHorarioInicio());
        entity.setHorarioFim(dto.getHorarioFim());
    }
}
