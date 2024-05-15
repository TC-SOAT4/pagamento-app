package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockPagamentoGateway implements PagamentoGateway {

    public static final Logger logger= LoggerFactory.getLogger(MockPagamentoGateway.class);

    @Override
    public boolean aprovar(Pagamento pagamento){
        boolean aprovacao = Math.random() < 0.9; //chance de sucesso.
        logger.trace("Resultado da aprovação: {}", aprovacao);
        return aprovacao;
    }

}
