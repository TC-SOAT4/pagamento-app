package com.fiap.pagamentoapp.application.pedido.usecase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatusPedidoDTO {

    private Integer idPedido;
    private String status;
    @Builder.Default
    private String taskApp = "PagamentoApp";

}
