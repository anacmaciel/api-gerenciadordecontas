package com.catalisa.gerenciadordecontas.repository;

import com.catalisa.gerenciadordecontas.model.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {
}
