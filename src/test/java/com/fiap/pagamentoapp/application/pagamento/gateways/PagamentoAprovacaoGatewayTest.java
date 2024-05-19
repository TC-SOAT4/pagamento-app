package com.fiap.pagamentoapp.application.pagamento.gateways;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PagamentoAprovacaoGatewayTest {

    @Test
    void testAprovarPagamento() {
        // Criação de um mock da interface PagamentoAprovacaoGateway
        PagamentoAprovacaoGateway pagamentoAprovacaoGateway = Mockito.mock(PagamentoAprovacaoGateway.class);

        // Criação de um objeto Pagamento de teste
        Pagamento pagamento = new Pagamento();
        pagamento.setId("1");
        pagamento.setValor(BigDecimal.valueOf(100.0));

        // Configuração do comportamento do mock para retornar true quando o método aprovar for chamado
        when(pagamentoAprovacaoGateway.aprovar(pagamento)).thenReturn(true);

        // Verificação se o método aprovar retorna true
        boolean resultado = pagamentoAprovacaoGateway.aprovar(pagamento);
        assertTrue(resultado, "O pagamento deve ser aprovado");
    }
}
