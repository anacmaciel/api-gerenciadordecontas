package com.catalisa.gerenciadordecontas.repository;

import com.catalisa.gerenciadordecontas.model.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
}
