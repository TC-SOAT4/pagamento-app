package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import org.springframework.stereotype.Component;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.AtualizarStatusPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AtualizarStatusPagamentoUseCaseImpl implements AtualizarStatusPagamentoUseCase {

     private final PagamentoGateway pagamentoGateway;

    @Override
    public void executar(String idPagamento, boolean pagamentoConfirmado) {
        var pagamento = pagamentoGateway.buscar(idPagamento);
        pagamento.setStatusPagamento(pagamentoConfirmado ? StatusPagamento.APROVADO : StatusPagamento.RECUSADO);
        pagamentoGateway.salvar(pagamento);
    }

}
