package com.fiap.pagamentoapp.domain.pagamento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagamentos")
public class Pagamento {

    @Id
    private String id;
    private Integer idPedido;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDateTime data;


    public Pagamento(Integer idPedido, BigDecimal valor, DateTimeFormat data) {
        this.id = UUID.randomUUID().toString();
        this.idPedido = idPedido;
        this.valor = valor;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
    }
}
