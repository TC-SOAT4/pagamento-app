package com.fiap.pagamentoapp.application.usecases;

import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoAprovacaoGateway;
import com.fiap.pagamentoapp.application.pagamento.gateways.PagamentoGateway;
import com.fiap.pagamentoapp.application.pagamento.usecases.impl.ProcessarPagamentoUseCaseImpl;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProcessarPagamentoUseCaseImplTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoAprovacaoGateway pagamentoAprovacaoGateway;

    @InjectMocks
    private ProcessarPagamentoUseCaseImpl processarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecutarAprovado() {
        Pagamento pagamento = new Pagamento();
        when(pagamentoAprovacaoGateway.aprovar(pagamento)).thenReturn(true);
        when(pagamentoGateway.salvar(pagamento)).thenReturn(pagamento);

        Pagamento resultado = processarPagamentoUseCase.executar(pagamento);

        assertEquals(StatusPagamento.APROVADO, resultado.getStatusPagamento());
        verify(pagamentoAprovacaoGateway, times(1)).aprovar(pagamento);
        verify(pagamentoGateway, times(1)).salvar(pagamento);
    }

    @Test
    void testExecutarRecusado() {
        Pagamento pagamento = new Pagamento();
        when(pagamentoAprovacaoGateway.aprovar(pagamento)).thenReturn(false);
        when(pagamentoGateway.salvar(pagamento)).thenReturn(pagamento);

        Pagamento resultado = processarPagamentoUseCase.executar(pagamento);

        assertEquals(StatusPagamento.RECUSADO, resultado.getStatusPagamento());
        verify(pagamentoAprovacaoGateway, times(1)).aprovar(pagamento);
        verify(pagamentoGateway, times(1)).salvar(pagamento);
    }
}
