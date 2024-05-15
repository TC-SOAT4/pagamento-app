package com.fiap.pagamentoapp.api.controller.dto;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarPagamentoResponse {

    private String id;
    private Integer idPedido;


    public ListarPagamentoResponse(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.idPedido = pagamento.getIdPedido();
    }
}
