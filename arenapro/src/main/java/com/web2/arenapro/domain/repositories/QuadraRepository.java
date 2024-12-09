package com.web2.arenapro.domain.repositories;

import com.web2.arenapro.domain.entities.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {
}
