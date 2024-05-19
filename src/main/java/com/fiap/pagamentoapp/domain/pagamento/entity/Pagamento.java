package com.fiap.pagamentoapp.domain.pagamento.entity;

import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;

    public Pagamento(PagamentoDocument document) {
        this.id = document.getId();
        this.idPedido = document.getIdPedido();
        this.valor = document.getValor();
        this.statusPagamento = StatusPagamento.valueOf(document.getStatusPagamento().toString());
        this.data = document.getData();
    }

    @Override
    public String toString() {
        return "Pagamento(id=" + id +
                ", idPedido=" + idPedido +
                ", valor=" + valor +
                ", statusPagamento=" + statusPagamento +
                ", data=" + data + ")";
    }
}
