package com.fiap.pagamentoapp.application.pagamento.usecases;

public interface AtualizarStatusPagamentoUseCase {

    void executar(String idPagamento, boolean pagamentoConfirmado);

}
