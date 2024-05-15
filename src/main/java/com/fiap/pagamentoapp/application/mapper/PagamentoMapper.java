package com.fiap.pagamentoapp.application.mapper;

import com.fiap.pagamentoapp.api.controller.dto.PagamentoResponse;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {


    //// De/para DTO
    public static Pagamento processarPagamentoRequestParaPagamento(ProcessarPagamentoRequest request) {
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(request.getIdPedido());
        pagamento.setValor(request.getValor());
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        return pagamento;
    }

    public static PagamentoResponse pagamentoParaPagamentoResponse(Pagamento pagamento){
        PagamentoResponse response = new PagamentoResponse();
        response.setId(pagamento.getId());
        response.setIdPedido(pagamento.getIdPedido());
        response.setValor(pagamento.getValor());
        response.setStatusPagamento(pagamento.getStatusPagamento());
        response.setData(pagamento.getData());
        return response;
    }


    //// De/Para Document
    public Pagamento documentPagamentoParaPagamento(PagamentoDocument document) {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(document.getId());
        pagamento.setIdPedido(document.getIdPedido());
        pagamento.setValor(document.getValor());
        pagamento.setStatusPagamento(StatusPagamento.valueOf(String.valueOf(document.getStatusPagamento())));
        pagamento.setData(document.getData());
        return pagamento;
    }

}
