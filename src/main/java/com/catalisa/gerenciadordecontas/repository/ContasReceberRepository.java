package com.catalisa.gerenciadordecontas.repository;

import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasReceberRepository extends JpaRepository<ContasReceberModel, Long> {
}
