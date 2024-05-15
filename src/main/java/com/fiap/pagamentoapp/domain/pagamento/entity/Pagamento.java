package com.fiap.pagamentoapp.domain.pagamento.entity;

import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;

    public Pagamento(Integer idPedido, BigDecimal valor) {
        this.id = UUID.randomUUID().toString();
        this.idPedido = idPedido;
        this.valor = valor;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
    }


    public Pagamento(PagamentoDocument document) {
        this.id = document.getId();
        this.idPedido = document.getIdPedido();
        this.valor = document.getValor();
        this.statusPagamento = document.getStatusPagamento();
        this.data = document.getData();
    }


}
