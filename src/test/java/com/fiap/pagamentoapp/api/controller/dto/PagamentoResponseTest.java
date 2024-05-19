package com.fiap.pagamentoapp.api.controller.dto;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagamentoResponseTest {

    private PagamentoResponse response1;
    private PagamentoResponse response2;
    private PagamentoResponse response3;
    private PagamentoResponse response4;


    @BeforeEach
    void setUp() {
        var data = LocalDateTime.now();
        response1 = new PagamentoResponse("123", 456, new BigDecimal("100.00"), StatusPagamento.PENDENTE, data);
        response2 = new PagamentoResponse("123", 456, new BigDecimal("100.00"), StatusPagamento.PENDENTE, data);
        response3 = new PagamentoResponse("124", 456, new BigDecimal("100.00"), StatusPagamento.PENDENTE, data);
        response4 = new PagamentoResponse("123", 457, new BigDecimal("100.00"), StatusPagamento.PENDENTE, data);

    }
    

    @Test
    void testEquals() {
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertNotEquals(response1, response4);
    }

    @Test
    void testHashCode() {
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
        assertNotEquals(response1.hashCode(), response4.hashCode());
        assertNotEquals(response1.hashCode(), new PagamentoResponse().hashCode());
    }

    @Test
    void testToString() {
        assertTrue(response1.toString().contains("PagamentoResponse"));
    }
}
