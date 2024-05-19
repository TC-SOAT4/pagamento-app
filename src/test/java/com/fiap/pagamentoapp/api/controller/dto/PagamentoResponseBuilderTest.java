package com.fiap.pagamentoapp.api.controller.dto;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoResponseBuilderTest {

    @Test
    void testToString() {
        LocalDateTime data = LocalDateTime.now();
        PagamentoResponse response = PagamentoResponse.builder()
                .id("123")
                .idPedido(456)
                .valor(BigDecimal.valueOf(100.0))
                .statusPagamento(StatusPagamento.PENDENTE)
                .data(data)
                .build();

        String expected = "PagamentoResponse{id='123', idPedido=456, valor=100.0, statusPagamento=PENDENTE, data=" + data.toString() + "}";
        assertEquals(expected, response.toString());
    }

    @Test
    void testBuilder() {
        LocalDateTime data = LocalDateTime.now();
        PagamentoResponse response = PagamentoResponse.builder()
                .id("123")
                .idPedido(456)
                .valor(BigDecimal.valueOf(100.0))
                .statusPagamento(StatusPagamento.PENDENTE)
                .data(data)
                .build();

        assertEquals("123", response.getId());
        assertEquals(456, response.getIdPedido());
        assertEquals(BigDecimal.valueOf(100.0), response.getValor());
        assertEquals(StatusPagamento.PENDENTE, response.getStatusPagamento());
        assertEquals(data, response.getData());
    }
}
