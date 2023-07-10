package com.m3s02exercicios.despesas.repositories;

import com.m3s02exercicios.despesas.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByStatus(String status);

}
