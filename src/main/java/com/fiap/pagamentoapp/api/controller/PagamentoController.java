package com.fiap.pagamentoapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.mapper.PagamentoMapper;
import com.fiap.pagamentoapp.application.pagamento.usecases.AtualizarStatusPagamentoUseCase;
import com.fiap.pagamentoapp.application.pagamento.usecases.ConfirmarPagamentoUseCase;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final ConfirmarPagamentoUseCase confirmarPagamentoUseCase;
    private final AtualizarStatusPagamentoUseCase atualizarStatusPagamentoUseCase;

    @PostMapping
    @Operation(summary = "Criar novo pagamento", description = "Criar novo pagamento")
    public ResponseEntity<PagamentoResponse> processar(@RequestBody ProcessarPagamentoRequest request) {
        Pagamento pagamento = Pagamento.builder()
                .idPedido(request.getIdPedido())
                .valor(request.getValor())
                .build();

        Pagamento resultado = processarPagamentoUseCase.executar(pagamento);
        PagamentoResponse response = PagamentoMapper.pagamentoParaPagamentoResponse(resultado); // Chamando o método
                                                                                                // estático

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{idPagamento}")
    @Operation(summary = "Confirmar pagamento", description = "Confirmar pagamento")
    public ResponseEntity<Void> processar(@PathVariable String idPagamento) {
        confirmarPagamentoUseCase.executar(idPagamento);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idPagamento}/atualizar-status-pagamento")
    @Operation(summary = "Webhook - Atualizar Status pagamento", description = "Webhook para atualizar status do pagamento pedido")
    public ResponseEntity<String> atualizarStatusPagamento(@PathVariable String idPagamento,
            @RequestBody boolean pagamentoConfirmado) {
        atualizarStatusPagamentoUseCase.executar(idPagamento, pagamentoConfirmado);
        return ResponseEntity.ok().body("Informação de pagamento recebida com sucesso para o id " + idPagamento);
    }
}
