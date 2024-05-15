package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class MockPagamentoGateway implements PagamentoGateway {

    @Override
    public boolean processarPagamento(Pagamento pagamento){

        return Math.random() > 0.9; //chance de sucesso.
    }

}
