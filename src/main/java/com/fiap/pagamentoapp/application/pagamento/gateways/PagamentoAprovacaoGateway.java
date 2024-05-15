package com.fiap.pagamentoapp.application.pagamento.gateways;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

public interface PagamentoAprovacaoGateway {

    boolean aprovar(Pagamento pagamento);

}
