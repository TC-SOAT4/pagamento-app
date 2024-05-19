package com.fiap.pagamentoapp.domain.pagamento.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoTest {

    @Test
    void testPagamento() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId("1");
        pagamento.setIdPedido(101);
        pagamento.setValor(BigDecimal.valueOf(150.0));
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        pagamento.setData(LocalDateTime.now());

        assertEquals("1", pagamento.getId());
        assertEquals(101, pagamento.getIdPedido());
        assertEquals(BigDecimal.valueOf(150.0), pagamento.getValor());
        assertEquals(StatusPagamento.APROVADO, pagamento.getStatusPagamento());
        assertEquals(LocalDateTime.now().getDayOfYear(), pagamento.getData().getDayOfYear());
    }
}
