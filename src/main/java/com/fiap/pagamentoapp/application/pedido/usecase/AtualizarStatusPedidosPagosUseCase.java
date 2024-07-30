package com.fiap.pagamentoapp.application.pedido.usecase;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

public interface AtualizarStatusPedidosPagosUseCase {

    void executar(Pagamento pagamento);
}
