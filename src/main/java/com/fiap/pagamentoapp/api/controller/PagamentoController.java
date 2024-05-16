package com.fiap.pagamentoapp.api.controller;

import com.fiap.pagamentoapp.api.controller.dto.ListarPagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.mapper.PagamentoMapper;
import com.fiap.pagamentoapp.application.pagamento.usecases.ListarPagamentosUseCase;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private static final Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    @Autowired
    private ProcessarPagamentoUseCase processarPagamento;
    @Autowired
    private ListarPagamentosUseCase listarPagamentosUseCase;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping
    @Operation(summary = "Processar pagamento", description = "Processa um pagamento")
    public ResponseEntity<PagamentoResponse> processar(@RequestBody ProcessarPagamentoRequest request){
        Pagamento pagamento = PagamentoMapper.processarPagamentoRequestParaPagamento(request);
        pagamento = processarPagamento.executar(pagamento);
        if(pagamento.getStatusPagamento() == StatusPagamento.RECUSADO){
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(PagamentoMapper.pagamentoParaPagamentoResponse(pagamento));
        }
        return ResponseEntity.status(HttpStatus.OK).body(PagamentoMapper.pagamentoParaPagamentoResponse(pagamento));
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos")
    public ResponseEntity<List<ListarPagamentoResponse>> listarTodos(){
        return criarResposta(listarPagamentosUseCase.listarTodos().stream()
                .map(PagamentoMapper::pagamentoParaListarPagamentoResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por id")
    public ResponseEntity<PagamentoResponse> obterPorId(@PathVariable String id) {
        return listarPagamentosUseCase.buscarPorIdPagamento(id)
                .map(pagamento -> ResponseEntity.ok(PagamentoMapper.pagamentoParaPagamentoResponse(pagamento)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PagamentoResponse>> listarPorStatus(@PathVariable StatusPagamento status) {
        List<PagamentoResponse> pagamentos = listarPagamentosUseCase.listarPorStatus(status)
                .stream()
                .map(PagamentoMapper::pagamentoParaPagamentoResponse)
                .collect(Collectors.toList());
        return criarResposta(pagamentos);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<PagamentoResponse>> listarPorIdPedido(@PathVariable Integer idPedido) {
        List<PagamentoResponse> pagamentos = listarPagamentosUseCase.listarPorIdPedido(idPedido)
                .stream()
                .map(PagamentoMapper::pagamentoParaPagamentoResponse)
                .collect(Collectors.toList());
        return criarResposta(pagamentos);
    }

    @GetMapping("/data")
    public ResponseEntity<List<PagamentoResponse>> listarPorData(
            @RequestParam String inicio,
            @RequestParam String fim) {

        LocalDateTime inicioDateTime = parseDateTime(inicio);
        LocalDateTime fimDateTime = parseDateTime(fim);

        List<PagamentoResponse> pagamentos = listarPagamentosUseCase.listarPorPeriodo(inicioDateTime, fimDateTime)
                .stream()
                .map(PagamentoMapper::pagamentoParaPagamentoResponse)
                .collect(Collectors.toList());
        return criarResposta(pagamentos);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            // Tenta parsear como data se apenas a data foi fornecida
            LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ISO_DATE);
            return date.atStartOfDay(); // Usa meia-noite do dia
        }
    }
    private <T> ResponseEntity<List<T>> criarResposta(List<T> list){
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
}
