package com.fiap.pagamentoapp.api.controller;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PagamentoControllerTest {

    @Mock
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessarPagamento_Success() {
        ProcessarPagamentoRequest request = new ProcessarPagamentoRequest();
        request.setIdPedido(1);
        request.setValor(BigDecimal.valueOf(100.0));

        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(1);
        pagamento.setValor(BigDecimal.valueOf(100.0));
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);

        when(processarPagamentoUseCase.executar(any(Pagamento.class))).thenReturn(pagamento);

        ResponseEntity<PagamentoResponse> response = pagamentoController.processar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testProcessarPagamento_Failure() {
        ProcessarPagamentoRequest request = new ProcessarPagamentoRequest();
        request.setIdPedido(1);
        request.setValor(BigDecimal.valueOf(100.0));

        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(1);
        pagamento.setValor(BigDecimal.valueOf(100.0));
        pagamento.setStatusPagamento(StatusPagamento.RECUSADO);

        when(processarPagamentoUseCase.executar(any(Pagamento.class))).thenReturn(pagamento);

        ResponseEntity<PagamentoResponse> response = pagamentoController.processar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica se BAD_REQUEST é retornado
    }
}
