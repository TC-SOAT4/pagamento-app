package application.pagamento.gateways;

import domain.pagamento.entity.Pagamento;

public interface PagamentoGateway {

    boolean processarPagamento(Pagamento pagamento);

}
