package com.fiap.pagamentoapp.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagamentosResponse {

    List<PagamentoResponse> pagamentos;

}
