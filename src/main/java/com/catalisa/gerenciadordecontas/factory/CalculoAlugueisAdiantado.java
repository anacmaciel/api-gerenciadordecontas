package com.catalisa.gerenciadordecontas.factory;

import java.math.BigDecimal;

public class CalculoAlugueisAdiantado implements ICalculoAlugueis{
    @Override
    public BigDecimal calculo(BigDecimal valorRecebido) {
        BigDecimal abatimento = valorRecebido.multiply(new BigDecimal(0.05));
        BigDecimal resultado = valorRecebido.add(abatimento);

        return resultado;
    }
}
