package com.fiap.pagamentoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge - Pagamento API")
                        .description(
                                "Trabalho final FIAP/Alura - API de pagamentos")
                        .contact(new Contact()
                                .name("Time")));
    }

}