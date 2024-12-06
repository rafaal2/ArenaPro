package com.web2.arenapro.domain.repositories;

import com.web2.arenapro.domain.entities.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {

<<<<<<< HEAD
=======
    Optional<Quadra> findByName(String name);

    Optional<Quadra> findAll(String name);
>>>>>>> 8ca82509d4c80032d72cc1426d5a635410d1c945
}
