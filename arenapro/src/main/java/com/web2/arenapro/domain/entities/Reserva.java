package com.web2.arenapro.domain.entities;

import com.web2.arenapro.domain.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "quadra_id", nullable = false)
    //private Quadra quadra;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusReserva status;

}
