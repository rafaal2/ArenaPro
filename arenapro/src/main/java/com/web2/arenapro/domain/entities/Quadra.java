package com.web2.arenapro.domain.entities;

import com.web2.arenapro.domain.enums.TiposQuadra;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quadra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
<<<<<<< HEAD
    private String tipo;
=======
    @Enumerated(EnumType.STRING)
    private TiposQuadra tipo;
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945

    @Column(nullable = false)
    private Integer capacidade;

    @Column(nullable = false)
    private Boolean status;

<<<<<<< HEAD
//    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Reserva> reservas;

//    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Horarios> horariosDisponiveis;
=======
    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horarios> horariosDisponiveis;
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945
}
