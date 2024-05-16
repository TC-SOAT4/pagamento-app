package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.application.mapper.PagamentoMapper;
import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PagamentoMongoGateway implements PagamentoGateway {
    private static final Logger logger = LoggerFactory.getLogger(PagamentoMongoGateway.class);
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoDocument document = new PagamentoDocument(pagamento);
        // Conversão de Pagamento para PagamentoEntity
        document = pagamentoRepository.save(document);
        return new Pagamento(document);
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll().stream()
                .map(PagamentoMapper::pagamentoDocumentParaPagamento)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> listarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findByStatusPagamento(status).stream()
                .map(PagamentoMapper::pagamentoDocumentParaPagamento)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> listarPorIdPedido(Integer idPedido) {
        return pagamentoRepository.findByIdPedido(idPedido).stream()
                .map(PagamentoMapper::pagamentoDocumentParaPagamento)
                .collect(Collectors.toList());
    }

    /*@Override
    public Optional<Pagamento> buscarPorIdPagamento(String idPagamento) {
        return pagamentoRepository.findByIdPagamento(idPagamento)
                .map(PagamentoMapper::pagamentoDocumentParaPagamento)
                .orElse(null);
    }*/
    @Override
    public Optional<Pagamento> buscarPorIdPagamento(String idPagamento) {
        return pagamentoRepository.findByIdPagamento(idPagamento)
                .map(PagamentoMapper::pagamentoDocumentParaPagamento);
    }

    @Override
    public List<Pagamento> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return pagamentoRepository.findByDataBetween(inicio, fim).stream()
                .map(PagamentoMapper::pagamentoDocumentParaPagamento)
                .collect(Collectors.toList());
    }

}
