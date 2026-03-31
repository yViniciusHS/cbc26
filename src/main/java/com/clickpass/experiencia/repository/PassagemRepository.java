package com.clickpass.experiencia.repository;

import com.clickpass.experiencia.model.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {
    // O JpaRepository já traz métodos prontos como save(), findById(), etc.
}