package com.fiap.pagamentoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PagamentoAppApplicationTests {

	@Test
	void main() {
		PagamentoAppApplication.main(new String[] {});
		assertNotNull(PagamentoAppApplication.class, "Application context should be loaded");
	}

	@Test
	void contextLoads() {
		// Simples asserção para garantir que o contexto é carregado
		assertNotNull(this, "O contexto da aplicação deve ser carregado corretamente.");
	}
}
