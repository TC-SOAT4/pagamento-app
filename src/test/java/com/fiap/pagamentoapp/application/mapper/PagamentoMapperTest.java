package com.fiap.pagamentoapp.application.mapper;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoMapperTest {

    @Test
    void testProcessarPagamentoRequestParaPagamento() {
        ProcessarPagamentoRequest request = new ProcessarPagamentoRequest();
        request.setIdPedido(1);
        request.setValor(BigDecimal.valueOf(100.0));

        Pagamento pagamento = PagamentoMapper.processarPagamentoRequestParaPagamento(request);

        assertEquals(1, pagamento.getIdPedido());
        assertEquals(BigDecimal.valueOf(100.0), pagamento.getValor());
        assertEquals(StatusPagamento.PENDENTE, pagamento.getStatusPagamento());
    }

    @Test
    void testPagamentoParaPagamentoResponse() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId("1");
        pagamento.setIdPedido(1);
        pagamento.setValor(BigDecimal.valueOf(100.0));
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        pagamento.setData(LocalDateTime.now());

        PagamentoResponse response = PagamentoMapper.pagamentoParaPagamentoResponse(pagamento);

        assertEquals("1", response.getId());
        assertEquals(1, response.getIdPedido());
        assertEquals(BigDecimal.valueOf(100.0), response.getValor());
        assertEquals(StatusPagamento.APROVADO, response.getStatusPagamento());
        assertEquals(pagamento.getData(), response.getData());
    }

    @Test
    void testPagamentoDocumentParaPagamento() {
        PagamentoDocument document = new PagamentoDocument();
        document.setId("1");
        document.setIdPedido(1);
        document.setValor(BigDecimal.valueOf(100.0));
        document.setStatusPagamento(StatusPagamento.APROVADO);
        document.setData(LocalDateTime.now());

        Pagamento pagamento = PagamentoMapper.pagamentoDocumentParaPagamento(document);

        assertEquals("1", pagamento.getId());
        assertEquals(1, pagamento.getIdPedido());
        assertEquals(BigDecimal.valueOf(100.0), pagamento.getValor());
        assertEquals(StatusPagamento.APROVADO, pagamento.getStatusPagamento());
        assertEquals(document.getData(), pagamento.getData());
    }
}
