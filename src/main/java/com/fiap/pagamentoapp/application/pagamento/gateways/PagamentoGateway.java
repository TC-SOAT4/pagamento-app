package com.fiap.pagamentoapp.application.pagamento.gateways;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoGateway {

     Pagamento salvar(Pagamento pagamento);
     List<Pagamento> listarTodos();
     List<Pagamento> listarPorStatus(StatusPagamento status);
     List<Pagamento> listarPorIdPedido(Integer idPedido);
     Pagamento buscarPorIdPagamento(String idPagamento);
     List<Pagamento> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
}
