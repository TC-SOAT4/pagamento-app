package com.fiap.pagamentoapp.infrastructure.pagamento.repository;

import com.fiap.pagamentoapp.domain.pagamento.entity.Pagamento;
import com.fiap.pagamentoapp.domain.pagamento.entity.StatusPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoRepository extends MongoRepository<Pagamento, String>{

    List<Pagamento> findByStatusPagamento(StatusPagamento status);

    List<Pagamento> findByIdPedido(Integer pedido);

    @Query("{ 'id' : ?0 }")
    Pagamento findByIdPagamento(String Id);

    @Query("{'data' : {$gte: ?0, $lte: ?1 } }")
    List<Pagamento> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
}
