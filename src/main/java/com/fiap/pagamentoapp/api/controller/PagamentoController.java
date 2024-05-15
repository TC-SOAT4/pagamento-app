package com.fiap.pagamentoapp.api.controller;

import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.repository.PagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Autowired
    private ProcessarPagamento processarPagamento;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping
    @Operation(summary = "Processar pagamento", description = "Processa um pagamento")
    public ResponseEntity<Pagamento> processar(@RequestBody Pagamento pagamento){
        Pagamento resultado = processarPagamento.executar(pagamento);
        if(resultado.getStatusPagamento() == StatusPagamento.RECUSADO){
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(resultado);
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos")
    public ResponseEntity<List<Pagamento>> listarTodos(){
        return criarResposta(pagamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por id")
    public ResponseEntity<Pagamento> obterPorId(@PathVariable String id){
       Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
       return pagamento.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pagamento>> listarStatus(@PathVariable StatusPagamento status){
        return criarResposta(pagamentoRepository.findByStatusPagamento(status));
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<Pagamento>> listarPorIdPedido(@PathVariable Integer idPedido){
        return criarResposta(pagamentoRepository.findByIdPedido(idPedido));
    }

    @GetMapping("/data")
    public ResponseEntity<List<Pagamento>> listarPorData(
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
