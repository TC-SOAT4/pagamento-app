package infrastructure.pagamento.gateway;

import application.pagamento.gateways.PagamentoGateway;
import domain.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class MockPagamentoGateway implements PagamentoGateway {

    @Override
    public boolean processarPagamento(Pagamento pagamento){

        return Math.random() > 0.9; //chance de sucesso.
    }

}
