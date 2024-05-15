package com.fiap.pagamentoapp.api.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProcessarPagamentoRequest {

    @NotNull
    private Integer idPedido;
    @NotNull
    private BigDecimal valor;

}
