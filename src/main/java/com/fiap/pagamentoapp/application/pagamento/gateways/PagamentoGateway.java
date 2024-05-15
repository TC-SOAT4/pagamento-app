package com.fiap.pagamentoapp.application.pagamento.gateways;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

public interface PagamentoGateway {

    boolean aprovar(Pagamento pagamento);

}
