package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ConfirmarPagamentoUseCase;
import com.fiap.pagamentoapp.application.pedido.usecase.AtualizarStatusPedidosPagosUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ConfirmarPagamentoUseCaseImpl implements ConfirmarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final AtualizarStatusPedidosPagosUseCase atualizarStatusPedidosPagosUseCase;

    @Transactional
    @Override
    public void executar(String id) {
        var pagamento = pagamentoGateway.buscar(id);
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        var pagamentoConfirmado = pagamentoGateway.salvar(pagamento);
        atualizarStatusPedidosPagosUseCase.executar(pagamentoConfirmado);
    }

}
