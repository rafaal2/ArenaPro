package com.web2.arenapro.domain.dtos;

import com.web2.arenapro.domain.entities.Quadra;
import com.web2.arenapro.domain.enums.TiposQuadra;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuadraDTO {

    private Long id;

    @NotBlank(message = "O nome da quadra é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "A localização da quadra é obrigatória")
    @Size(max = 200, message = "A localização deve ter no máximo 200 caracteres")
    private String localizacao;

    @NotNull(message = "O tipo da quadra é obrigatório")
<<<<<<< HEAD
    private String tipo;
=======
    private TiposQuadra tipo;
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945

    @NotNull(message = "A capacidade é obrigatória")
    private Integer capacidade;

    @NotNull(message = "O status da quadra é obrigatório")
    private Boolean status;

    public QuadraDTO(Quadra entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.localizacao = entity.getLocalizacao();
        this.tipo = entity.getTipo();
        this.capacidade = entity.getCapacidade();
        this.status = entity.getStatus();
    }
}
