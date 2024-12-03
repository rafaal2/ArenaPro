package com.web2.arenapro.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web2.arenapro.domain.entities.Reserva;
import com.web2.arenapro.domain.enums.StatusReserva;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    String tor = "tor";

    private Long id;

    @NotNull(message = "Quadra obrigatória")
    private Long quadraId;

    @JsonProperty("usuarioId")
    @NotNull(message = "Usuário obrigatório")
    private Long usuarioId;

    @NotNull(message = "Data obrigatória")
    @Future(message = "A data deve estar no futuro")
    private LocalDate data;

    @NotNull(message = "Data e a hora obrigatórias")
    @Future(message = "A data e a hora devem estar no futuro")
    private LocalTime horarioInicio;

    @NotNull(message = "Duração obrigatória")
    private Integer duracao;

    @NotBlank(message = "Status obrigatório")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres")
    private StatusReserva status;

    public ReservaDTO(Reserva entity) {
        this.id = entity.getId();
        this.usuarioId = entity.getUsuario().getId();;
        //this.quadraId = entity.getQuadra().getId();
        this.horarioInicio = entity.getHorarioInicio();
        this.duracao = entity.getDuracao();
        this.status = entity.getStatus();
    }
}