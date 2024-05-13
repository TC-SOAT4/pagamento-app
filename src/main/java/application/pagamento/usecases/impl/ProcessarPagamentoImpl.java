package application.pagamento.usecases.impl;

import application.pagamento.gateways.PagamentoGateway;
import application.pagamento.usecases.ProcessarPagamento;
import domain.pagamento.entity.Pagamento;
import domain.pagamento.entity.StatusPagamento;
import infrastructure.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoImpl implements ProcessarPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoGateway pagamentoGateway;

    @Override
    public Pagamento executar(Pagamento pagamento){
        boolean aprovacao = pagamentoGateway.processarPagamento(pagamento);
        pagamento.setStatusPagamento(aprovacao ? StatusPagamento.APROVADO: StatusPagamento.RECUSADO);
        return pagamentoRepository.save(pagamento);
    }

}
