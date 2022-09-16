package com.catalisa.gerenciadordecontas.enums;

import java.time.LocalDate;

public enum RecebimentoAlugueis {
EM_ATRASO,
    EM_DIA,
    ADIANTADO;
    public static RecebimentoAlugueis validarRecebimentoAluguel(LocalDate dataDeVencimento) {
            LocalDate dataDeHoje = LocalDate.now();
            if (dataDeVencimento.isAfter(dataDeHoje)) {
                return ADIANTADO;
            } else if (dataDeVencimento.isBefore(dataDeHoje)) {
                return EM_ATRASO;
            } else {
                return EM_DIA;
            }
    }

}
