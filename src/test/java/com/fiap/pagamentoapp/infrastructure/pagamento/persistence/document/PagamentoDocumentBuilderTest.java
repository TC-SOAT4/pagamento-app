package com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PagamentoDocumentBuilderTest {

    @Test
    void testToString() {
        LocalDateTime data = LocalDateTime.now();
        PagamentoDocument pagamentoDocument = PagamentoDocument.builder()
                .id("123")
                .idPedido(456)
                .valor(BigDecimal.valueOf(100.0))
                .statusPagamento(StatusPagamento.PENDENTE)
                .data(data)
                .build();

        String expected = "PagamentoDocument(id=123, idPedido=456, valor=100.0, statusPagamento=PENDENTE, data=" + data.toString() + ")";
        assertEquals(expected, pagamentoDocument.toString());
    }

    @Test
    void testBuilder() {
        LocalDateTime data = LocalDateTime.now();
        PagamentoDocument pagamentoDocument = PagamentoDocument.builder()
                .id("123")
                .idPedido(456)
                .valor(BigDecimal.valueOf(100.0))
                .statusPagamento(StatusPagamento.PENDENTE)
                .data(data)
                .build();

        assertEquals("123", pagamentoDocument.getId());
        assertEquals(456, pagamentoDocument.getIdPedido());
        assertEquals(BigDecimal.valueOf(100.0), pagamentoDocument.getValor());
        assertEquals(StatusPagamento.PENDENTE, pagamentoDocument.getStatusPagamento());
        assertEquals(data, pagamentoDocument.getData());
    }
}
