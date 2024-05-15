package com.fiap.pagamentoapp.application.pagamento.usecases.impl;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.repository.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoImpl implements ProcessarPagamento {

    private static final Logger logger = LoggerFactory.getLogger(ProcessarPagamentoImpl.class);

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoGateway pagamentoGateway;

    @Override
    public Pagamento executar(Pagamento pagamento){
        boolean aprovacao = pagamentoGateway.aprovar(pagamento);
        logger.trace("Retorno aprovação: ", aprovacao);
        pagamento.setStatusPagamento(aprovacao ? StatusPagamento.APROVADO: StatusPagamento.RECUSADO);
        return pagamentoRepository.save(pagamento);
    }

}
