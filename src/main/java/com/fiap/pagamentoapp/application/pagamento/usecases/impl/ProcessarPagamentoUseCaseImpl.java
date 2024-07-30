package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    @Override
    @Transactional
    public Pagamento executar(Pagamento pagamento) {
        pagamento.setId(UUID.randomUUID().toString());
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        return pagamentoGateway.salvar(pagamento);
    }
}
