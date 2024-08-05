package com.fiap.pagamentoapp.infrastructure.pagamento.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository.PagamentoRepository;

@DataMongoTest
@Import(PagamentoMongoGateway.class)
@ActiveProfiles("test")
class PagamentoMongoGatewayTest {

    private final PagamentoMongoGateway pagamentoMongoGateway;
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoMongoGatewayTest(PagamentoMongoGateway pagamentoMongoGateway, PagamentoRepository pagamentoRepository) {
        this.pagamentoMongoGateway = pagamentoMongoGateway;
        this.pagamentoRepository = pagamentoRepository;
    }

    @BeforeEach
    void setup() {
        pagamentoRepository.deleteAll();
    }

    @Test
    void testSalvar() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId("1");
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);

        Pagamento saved = pagamentoMongoGateway.salvar(pagamento);
        assertNotNull(saved);
        assertEquals(StatusPagamento.APROVADO, saved.getStatusPagamento());
    }
}