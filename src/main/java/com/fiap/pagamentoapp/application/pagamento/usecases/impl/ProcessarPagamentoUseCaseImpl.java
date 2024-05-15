package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcessarPagamentoUseCaseImpl.class);

    @Autowired
    private PagamentoGateway pagamentoGateway;
    @Autowired
    private PagamentoAprovacaoGateway pagamentoAprovacaoGateway;

    @Override
    public Pagamento executar(Pagamento pagamento){
        if (pagamentoAprovacaoGateway.aprovar(pagamento)) {
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);
            return pagamentoGateway.salvar(pagamento);
        }
        pagamento.setStatusPagamento(StatusPagamento.RECUSADO);
        return pagamento;
    }
}

