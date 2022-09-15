package com.catalisa.gerenciadordecontas.factory;

import com.catalisa.gerenciadordecontas.enums.RecebimentoAlugueis;
import com.catalisa.gerenciadordecontas.model.ContasReceberModel;

public class AlugueisFactory {
public ICalculoAlugueis iCalculoAlugueis(ContasReceberModel contasReceberModel) {
    if (contasReceberModel.getRecebimentoAlugueis().equals(RecebimentoAlugueis.EM_DIA)) {
        return new CalculoAlugueisEmDia();
    } else if (contasReceberModel.getRecebimentoAlugueis().equals(RecebimentoAlugueis.ADIANTADO)) {
        return new CalculoAlugueisAdiantado();

    } else if (contasReceberModel.getRecebimentoAlugueis().equals(RecebimentoAlugueis.EM_ATRASO)) {
        return new CalculoAlugueisEmAtraso();
    } else {
        return null;
    }
}
}
