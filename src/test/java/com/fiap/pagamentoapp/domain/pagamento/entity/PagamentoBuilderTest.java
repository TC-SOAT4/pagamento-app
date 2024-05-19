package com.fiap.pagamentoapp.domain.pagamento.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PagamentoBuilderTest {

    @Test
    void testBuilder() {
        String id = "123";
        Integer idPedido = 456;
        BigDecimal valor = new BigDecimal("100.00");
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        LocalDateTime data = LocalDateTime.now();

        Pagamento pagamento = Pagamento.builder()
                .id(id)
                .idPedido(idPedido)
                .valor(valor)
                .statusPagamento(statusPagamento)
                .data(data)
                .build();

        assertNotNull(pagamento);
        assertEquals(id, pagamento.getId());
        assertEquals(idPedido, pagamento.getIdPedido());
        assertEquals(valor, pagamento.getValor());
        assertEquals(statusPagamento, pagamento.getStatusPagamento());
        assertEquals(data, pagamento.getData());
    }

    @Test
    void testToString() {
        String id = "123";
        Integer idPedido = 456;
        BigDecimal valor = new BigDecimal("100.00");
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        LocalDateTime data = LocalDateTime.now();

        Pagamento pagamento = Pagamento.builder()
                .id(id)
                .idPedido(idPedido)
                .valor(valor)
                .statusPagamento(statusPagamento)
                .data(data)
                .build();

        String expectedToString = "Pagamento(id=123, idPedido=456, valor=100.00, statusPagamento=PENDENTE, data=" + data.toString() + ")";
        assertEquals(expectedToString, pagamento.toString());
    }
}
