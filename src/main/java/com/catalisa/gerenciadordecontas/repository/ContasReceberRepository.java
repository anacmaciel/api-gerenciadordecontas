package com.catalisa.gerenciadordecontas.repository;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContasReceberRepository extends JpaRepository<ContasReceberModel, Long> {
List<ContasReceberModel> findByStatus(Status status);
List<ContasReceberModel> findByTipoRecebimento(TipoRecebimento tipoRecebimento);
}
