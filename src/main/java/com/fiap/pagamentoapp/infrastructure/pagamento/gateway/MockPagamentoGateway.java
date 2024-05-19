package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MockPagamentoGateway implements PagamentoAprovacaoGateway {

    private final boolean aprovacaoForcada;

    // Construtor padrão
    @Autowired
    public MockPagamentoGateway() {
        this.aprovacaoForcada = false; // Valor padrão, pode ser alterado para fins de teste
    }

    // Construtor para injetar a aprovação forçada
    public MockPagamentoGateway(boolean aprovacaoForcada) {
        this.aprovacaoForcada = aprovacaoForcada;
    }

    @Override
    public boolean aprovar(Pagamento pagamento){
        boolean aprovacao = aprovacaoForcada || Math.random() < 0.1; //chance de sucesso.
        pagamento.setId(UUID.randomUUID().toString());
        pagamento.setData(LocalDateTime.now());
        return aprovacao;
    }
}
