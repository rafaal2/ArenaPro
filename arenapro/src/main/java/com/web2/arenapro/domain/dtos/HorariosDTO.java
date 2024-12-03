package com.web2.arenapro.domain.dtos;

import com.web2.arenapro.domain.entities.Horarios;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorariosDTO {

    private Long id;

    @NotNull(message = "O ID da quadra é obrigatório")
    private Long quadraId;

    @NotNull(message = "O dia da semana é obrigatório")
    private String diaSemana;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime horarioInicio;

    @NotNull(message = "O horário de fim é obrigatório")
    private LocalTime horarioFim;

    public HorariosDTO(Horarios entity) {
        this.id = entity.getId();
        this.quadraId = entity.getQuadra().getId();
        this.diaSemana = entity.getDiaSemana();
        this.horarioInicio = entity.getHorarioInicio();
        this.horarioFim = entity.getHorarioFim();
    }
}