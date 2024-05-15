package com.fiap.pagamentoapp.api.controller;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.mapper.PagamentoMapper;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private static final Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    @Autowired
    private ProcessarPagamentoUseCase processarPagamento;
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
    public ResponseEntity<List<PagamentoDocument>> listarTodos(){

        return criarResposta(pagamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por id")
    public ResponseEntity<PagamentoDocument> obterPorId(@PathVariable String id){
       Optional<PagamentoDocument> pagamento = pagamentoRepository.findById(id);
       return pagamento.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PagamentoDocument>> listarStatus(@PathVariable StatusPagamento status){
        return criarResposta(pagamentoRepository.findByStatusPagamento(status));
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<PagamentoDocument>> listarPorIdPedido(@PathVariable Integer idPedido){
        return criarResposta(pagamentoRepository.findByIdPedido(idPedido));
    }

    @GetMapping("/data")
    public ResponseEntity<List<PagamentoDocument>> listarPorData(
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim){

        if (inicio == null || fim == null) {
            if (inicio == null) {
                return ResponseEntity.badRequest().body(null);
            }
            // na ausência de recebimento de data fim, seta para a data atual.
            fim = LocalDateTime.now();
        }
        return criarResposta(pagamentoRepository.findByDataBetween(inicio, fim));
    }

    private <T> ResponseEntity<List<T>> criarResposta(List<T> list){
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
}
