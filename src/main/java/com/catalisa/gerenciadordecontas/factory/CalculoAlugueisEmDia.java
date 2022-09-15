package com.catalisa.gerenciadordecontas.factory;

import java.math.BigDecimal;

public class CalculoAlugueisEmDia implements ICalculoAlugueis {
    @Override
    public BigDecimal calculo(BigDecimal valorRecebido) {
        return valorRecebido;
    }
}
