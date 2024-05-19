package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockPagamentoGatewayTest {

    @Test
    void testAprovarPagamento_Sucesso() {
        MockPagamentoGateway gateway = new MockPagamentoGateway(true); // Forçando aprovação

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(BigDecimal.valueOf(100.0));
        boolean aprovado = gateway.aprovar(pagamento);

        assertTrue(aprovado, "Pagamento deve ser aprovado");
    }

    @Test
    void testAprovarPagamento_Falha() {
        MockPagamentoGateway gateway = new MockPagamentoGateway(false); // Forçando reprovação

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(BigDecimal.valueOf(100.0));
        boolean aprovado = gateway.aprovar(pagamento);

        assertFalse(aprovado, "Pagamento deve ser recusado");
    }
}
