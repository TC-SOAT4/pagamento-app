package com.fiap.pagamentoapp.infrastructure.pagamento.listeneres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pagamentoapp.api.controller.dto.ProcessarPagamentoRequest;
import com.fiap.pagamentoapp.application.pagamento.usecases.ProcessarPagamentoUseCase;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PedidoListener {

    Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @SqsListener("${aws.sqs.in.lanchonete.pagamento.queue.name}")
    public void receiveMessage(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProcessarPagamentoRequest processarPagamentoRequest = objectMapper.readValue(json,
        ProcessarPagamentoRequest.class);
        Pagamento pagamento = Pagamento.builder()
                .idPedido(processarPagamentoRequest.getIdPedido())
                .valor(processarPagamentoRequest.getValor())
                .build();
        processarPagamentoUseCase.executar(pagamento);
        logger.info("Recebido: {}", pagamento);
    }

}
