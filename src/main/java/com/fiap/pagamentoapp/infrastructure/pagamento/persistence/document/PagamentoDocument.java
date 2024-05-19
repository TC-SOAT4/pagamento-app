package com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagamentos")
public class PagamentoDocument {
    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;

    public PagamentoDocument(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.idPedido = pagamento.getIdPedido();
        this.valor = pagamento.getValor();
        this.statusPagamento = pagamento.getStatusPagamento();
        this.data = pagamento.getData();
    }

    @Override
    public String toString() {
        return "PagamentoDocument(id=" + id +
                ", idPedido=" + idPedido +
                ", valor=" + valor +
                ", statusPagamento=" + statusPagamento +
                ", data=" + data + ")";
    }
}
