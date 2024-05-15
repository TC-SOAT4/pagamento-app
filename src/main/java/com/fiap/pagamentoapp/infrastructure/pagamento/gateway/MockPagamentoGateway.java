package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MockPagamentoGateway implements PagamentoAprovacaoGateway {

    @Override
    public boolean aprovar(Pagamento pagamento){
        boolean aprovacao = Math.random() < 0.9; //chance de sucesso.
        pagamento.setData(LocalDateTime.now());
        return aprovacao;
    }

}
