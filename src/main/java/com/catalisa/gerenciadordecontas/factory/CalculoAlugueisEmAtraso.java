package com.catalisa.gerenciadordecontas.factory;

import java.math.BigDecimal;

public class CalculoAlugueisEmAtraso implements ICalculoAlugueis {
    @Override
    public BigDecimal calculo(BigDecimal valorRecebido) {
        BigDecimal multa = valorRecebido.multiply(new BigDecimal(0.035));
        BigDecimal resultado = valorRecebido.add(multa);
        return resultado;
    }
}
