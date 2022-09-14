package com.catalisa.gerenciadordecontas.factory;

import java.math.BigDecimal;

public interface ICalculoAlugueis {
    BigDecimal calculo(BigDecimal valorRecebido);
}
