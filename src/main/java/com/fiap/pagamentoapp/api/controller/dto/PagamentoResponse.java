package com.fiap.pagamentoapp.api.controller.dto;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponse {

    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;

    @Override
    public String toString() {
        return "PagamentoResponse{" +
                "id='" + id + '\'' +
                ", idPedido=" + idPedido +
                ", valor=" + valor +
                ", statusPagamento=" + statusPagamento +
                ", data=" + data +
                '}';
    }
}
