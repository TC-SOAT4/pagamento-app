package com.fiap.pagamentoapp.api.controller;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.mapper.PagamentoMapper;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Autowired
    public PagamentoController(ProcessarPagamentoUseCase processarPagamentoUseCase) {
        this.processarPagamentoUseCase = processarPagamentoUseCase;
    }

    @PostMapping("/processar")
    public ResponseEntity<PagamentoResponse> processar(@RequestBody ProcessarPagamentoRequest request) {
        Pagamento pagamento = Pagamento.builder()
                .idPedido(request.getIdPedido())
                .valor(request.getValor())
                .build();

        Pagamento resultado = processarPagamentoUseCase.executar(pagamento);
        PagamentoResponse response = PagamentoMapper.pagamentoParaPagamentoResponse(resultado); // Chamando o método estático

        if (resultado.getStatusPagamento() == StatusPagamento.APROVADO) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Alterado para BAD_REQUEST
        }
    }
}
