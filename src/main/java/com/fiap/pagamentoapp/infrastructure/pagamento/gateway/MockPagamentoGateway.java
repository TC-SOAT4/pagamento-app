package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MockPagamentoGateway implements PagamentoAprovacaoGateway {

    @Override
    public boolean aprovar(Pagamento pagamento){
        boolean aprovacao = Math.random() < 0.1; //chance de sucesso.
        pagamento.setId(UUID.randomUUID().toString());
        pagamento.setData(LocalDateTime.now());
        return aprovacao;
    }

}
