package com.catalisa.gerenciadordecontas.enums;

import java.time.LocalDate;

public enum Status {
    AGUARDANDO,
    PAGO,
    VENCIDA,
    EM_ABERTO;

    public static Status validarDatas(LocalDate dataDeCadastro, LocalDate dataDeVencimento) {
        if (dataDeVencimento.isBefore(dataDeCadastro)) {
            return VENCIDA;
        } else if (dataDeVencimento.isAfter(dataDeCadastro) || dataDeVencimento.equals(dataDeCadastro)) {
            return AGUARDANDO;
        } else {
            return EM_ABERTO;
        }


    }}