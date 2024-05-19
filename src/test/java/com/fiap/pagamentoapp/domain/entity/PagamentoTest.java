package com.fiap.pagamentoapp.domain.entity;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoTest {

    @Test
    void testEntityMethods() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId("123");
        pagamento.setIdPedido(456);
        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        pagamento.setData(LocalDateTime.now());

        assertEquals("123", pagamento.getId());
        assertEquals(456, pagamento.getIdPedido());
        assertEquals(new BigDecimal("100.00"), pagamento.getValor());
        assertEquals(StatusPagamento.PENDENTE, pagamento.getStatusPagamento());
        assertEquals(LocalDateTime.now().getDayOfMonth(), pagamento.getData().getDayOfMonth());
        
    }
}