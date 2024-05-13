package application.pagamento.usecases;

import domain.pagamento.entity.Pagamento;

public interface ProcessarPagamento {

    Pagamento executar(Pagamento pagamento);

}
