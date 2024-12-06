package com.web2.arenapro.application.services;

import com.web2.arenapro.application.services.exceptions.DatabaseException;
import com.web2.arenapro.application.services.exceptions.ResourceNotFoundException;
import com.web2.arenapro.domain.dtos.ReservaDTO;
import com.web2.arenapro.domain.dtos.UsuarioDTO;
import com.web2.arenapro.domain.entities.Reserva;
import com.web2.arenapro.domain.entities.Usuario;
import com.web2.arenapro.domain.repositories.QuadraRepository;
import com.web2.arenapro.domain.repositories.ReservaRepository;
import com.web2.arenapro.domain.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuadraRepository quadraRepository;

    @Transactional(readOnly = true)
    public ReservaDTO findById(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado")
        );
        return new ReservaDTO(reserva);
    }

    @Transactional
    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva entity = new Reserva();

        copyDtoToEntity(reservaDTO, entity);

        entity = reservaRepository.save(entity);

        return new ReservaDTO(entity);
    }

    @Transactional
    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {
        try {
            Reserva entity = reservaRepository.getReferenceById(id);

            copyDtoToEntity(reservaDTO, entity);

            entity = reservaRepository.save(entity);

            return new ReservaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado");
        }

        try {
            reservaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro no banco");
        }
    }

    private void copyDtoToEntity(ReservaDTO dto, Reserva entity) {

        System.out.println("Usuario ID no DTO: " + dto.getUsuarioId());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        System.out.println("Usuario encontrado: " + usuario.getId() + " - " + usuario.getNome());

        entity.setUsuario(usuario);
        entity.setData(dto.getData());
        entity.setHorarioInicio(dto.getHorarioInicio());
        entity.setDuracao(dto.getDuracao());
        entity.setStatus(dto.getStatus());
    }

}
