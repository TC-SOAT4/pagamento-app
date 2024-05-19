package com.fiap.pagamentoapp.domain.pagamento.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusPagamentoTest {

    @Test
    void testStatusPagamento() {
        assertEquals("APROVADO", StatusPagamento.APROVADO.name());
        assertEquals("RECUSADO", StatusPagamento.RECUSADO.name());
        assertEquals("PENDENTE", StatusPagamento.PENDENTE.name());
    }
}
