package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMongoGateway implements PagamentoGateway {
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoMongoGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoDocument document = new PagamentoDocument(pagamento);
        document = pagamentoRepository.save(document);
        return new Pagamento(document);
    }
}
