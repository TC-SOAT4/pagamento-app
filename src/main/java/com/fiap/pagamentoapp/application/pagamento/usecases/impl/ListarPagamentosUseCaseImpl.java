package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ListarPagamentosUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ListarPagamentosUseCaseImpl implements ListarPagamentosUseCase {
    @Autowired
    private PagamentoGateway pagamentoGateway;

    @Override
    public List<Pagamento> listarTodos() {
        return pagamentoGateway.listarTodos();
    }

    @Override
    public List<Pagamento> listarPorStatus(StatusPagamento status) {
        return pagamentoGateway.listarPorStatus(status);
    }

    @Override
    public List<Pagamento> listarPorIdPedido(Integer idPedido) {
        return pagamentoGateway.listarPorIdPedido(idPedido);
    }

    @Override
    public Pagamento buscarPorIdPagamento(String idPagamento) {
        return pagamentoGateway.buscarPorIdPagamento(idPagamento);
    }

    @Override
    public List<Pagamento> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return pagamentoGateway.listarPorPeriodo(inicio, fim);
    }
}
