package infrastructure.pagamento.repository;

import domain.pagamento.entity.Pagamento;
import domain.pagamento.entity.StatusPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoRepository extends MongoRepository<Pagamento, String>{

    List<Pagamento> findByStatusPagamento(StatusPagamento status);

    List<Pagamento> findByIdPedido(Integer pedido);

    Pagamento findByIdPagamento(String Id);

    @Query("{'data' : {$gte: ?0, $lte: ?1 } }")
    List<Pagamento> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
}
