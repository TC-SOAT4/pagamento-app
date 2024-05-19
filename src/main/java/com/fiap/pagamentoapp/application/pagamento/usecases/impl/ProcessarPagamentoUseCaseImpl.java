package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final PagamentoAprovacaoGateway pagamentoAprovacaoGateway;

    @Autowired
    public ProcessarPagamentoUseCaseImpl(PagamentoGateway pagamentoGateway, PagamentoAprovacaoGateway pagamentoAprovacaoGateway) {
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoAprovacaoGateway = pagamentoAprovacaoGateway;
    }

    @Override
    public Pagamento executar(Pagamento pagamento){
        if (pagamentoAprovacaoGateway.aprovar(pagamento)) {
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        } else {
            pagamento.setStatusPagamento(StatusPagamento.RECUSADO);
        }
        return pagamentoGateway.salvar(pagamento);
    }
}
