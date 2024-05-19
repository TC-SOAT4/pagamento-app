package com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoDocumentTest {

    @Test
    void testDocumentMethods() {
        PagamentoDocument document = new PagamentoDocument();
        document.setId("123");
        document.setIdPedido(456);
        document.setValor(new BigDecimal("100.00"));
        document.setStatusPagamento(StatusPagamento.valueOf("PENDENTE"));

        assertEquals("123", document.getId());
        assertEquals(456, document.getIdPedido());
        assertEquals(new BigDecimal("100.00"), document.getValor());
        assertEquals(StatusPagamento.valueOf("PENDENTE"), document.getStatusPagamento());
    }
}