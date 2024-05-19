package com.fiap.pagamentoapp.api.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProcessarPagamentoRequest {

    @NotNull
    private Integer idPedido;
    @NotNull
    private BigDecimal valor;

}
