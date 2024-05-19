package com.fiap.pagamentoapp.infrastructure.pagamento.persistence.repository;

import com.fiap.pagamentoapp.infrastructure.pagamento.persistence.document.PagamentoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagamentoRepository extends MongoRepository<PagamentoDocument, String>{

}
