package com.fiap.pagamentoapp.application.pagamento.usecases;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

public interface ProcessarPagamentoUseCase {

    Pagamento executar(Pagamento pagamento);

}
