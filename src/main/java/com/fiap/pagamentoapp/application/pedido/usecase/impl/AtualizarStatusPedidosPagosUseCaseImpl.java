package com.fiap.pagamentoapp.application.pedido.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pagamentoapp.application.pedido.usecase.AtualizarStatusPedidosPagosUseCase;
import com.fiap.pagamentoapp.application.pedido.usecase.dto.StatusPedidoDTO;
import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AtualizarStatusPedidosPagosUseCaseImpl implements AtualizarStatusPedidosPagosUseCase {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(AtualizarStatusPedidosPagosUseCaseImpl.class);

    @Value("${aws.sqs.out.pedido.status.uri}")
    private String uriPedidoPago;

    private static final String STATUS_PAGO = "PAGO";

    @Override
    public void executar(Pagamento pagamento) {
        try {
            var statusPago = StatusPedidoDTO.builder().idPedido(pagamento.getIdPedido()).status(STATUS_PAGO).build();
            String json = objectMapper.writeValueAsString(statusPago);
            sqsTemplate.send(uriPedidoPago, MessageBuilder.withPayload(json).build());
            logger.info("Pagamento confirmado. Atualizado status do pedodo: {}", pagamento);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }

    }

}
