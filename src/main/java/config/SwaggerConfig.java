package config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de pagamento", version="1.0", description = "Documentação da API de pagamento"))
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi pagamentoApi(){
        return GroupedOpenApi.builder()
                .group("springdoc")
                .packagesToScan("api.controller")
                .build();
    }

}
