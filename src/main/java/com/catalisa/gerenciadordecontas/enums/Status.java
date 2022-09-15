package com.catalisa.gerenciadordecontas.enums;

import java.time.LocalDate;

public enum Status {
    AGUARDANDO,
    PAGO,
    VENCIDA,
    EM_ABERTO;

    public static Status validarStatus(LocalDate dataDeCadastro, LocalDate dataDeVencimento) {
        if (dataDeCadastro.isBefore(dataDeVencimento) || dataDeCadastro.equals(dataDeVencimento)) {
            return AGUARDANDO;
        } else if (dataDeCadastro.isAfter(dataDeVencimento)) {
            return VENCIDA;
        } else {
            return EM_ABERTO;
        }

    }
}