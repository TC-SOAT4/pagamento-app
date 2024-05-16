package com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository;

import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends MongoRepository<PagamentoDocument, String>{

    List<PagamentoDocument> findByStatusPagamento(StatusPagamento status);

    List<PagamentoDocument> findByIdPedido(Integer pedido);

    @Query("{ 'id' : ?0 }")
    Optional<PagamentoDocument> findByIdPagamento(String Id);

    @Query("{'data' : {$gte: ?0, $lte: ?1 } }")
    List<PagamentoDocument> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
}
