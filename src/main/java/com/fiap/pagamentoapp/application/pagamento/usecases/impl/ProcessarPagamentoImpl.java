package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoImpl implements ProcessarPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoGateway pagamentoGateway;

    @Override
    public Pagamento executar(Pagamento pagamento){
        boolean aprovacao = pagamentoGateway.processarPagamento(pagamento);
        pagamento.setStatusPagamento(aprovacao ? StatusPagamento.APROVADO: StatusPagamento.RECUSADO);
        return pagamentoRepository.save(pagamento);
    }

}
