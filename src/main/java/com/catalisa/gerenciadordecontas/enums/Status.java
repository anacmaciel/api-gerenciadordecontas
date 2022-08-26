package com.catalisa.gerenciadordecontas.enums;

import java.time.LocalDate;

public enum Status {
    AGUARDANDO,
    PAGO,
    VENCIDA;

    public static Status validarDatas(LocalDate dataDeCadastro, LocalDate dataDeVencimento) {
        if (dataDeVencimento.isBefore(dataDeCadastro)) {
            return VENCIDA;
        } else {
            return AGUARDANDO;
        }

    }


}

