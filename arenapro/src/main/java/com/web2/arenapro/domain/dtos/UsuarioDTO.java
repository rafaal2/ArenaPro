package com.web2.arenapro.domain.dtos;

import com.web2.arenapro.domain.entities.Reserva;
import com.web2.arenapro.domain.entities.Usuario;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Nome necessário")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;
    @Size(min = 11, max = 11, message = "O CPF precisa ter 11 números")
    private String cpf;
    @NotBlank(message = "Email necessário")
    @Email(message = "Formato de email inválido")
    private String email;
    @NotBlank(message = "Senha necessária")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;
    @NotBlank(message = "Telefone necessário")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos")
    private String telefone;
    private List<ReservaDTO> reservas;

    public UsuarioDTO(Usuario entity) {

        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
        this.telefone = entity.getTelefone();
        this.reservas = entity.getReservas() != null
                ? entity.getReservas().stream().map(ReservaDTO::new).collect(Collectors.toList())
                : null;

    }

}

