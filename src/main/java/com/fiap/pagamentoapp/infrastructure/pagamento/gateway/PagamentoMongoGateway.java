package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import org.springframework.stereotype.Component;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PagamentoMongoGateway implements PagamentoGateway {
    private final PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoDocument document = new PagamentoDocument(pagamento);
        document = pagamentoRepository.save(document);
        return new Pagamento(document);
    }

    @Override
    public Pagamento buscar(String id) {
        var document = pagamentoRepository.findById(id).orElseThrow();
        return new Pagamento(document);
    }
}
