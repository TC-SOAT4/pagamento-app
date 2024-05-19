package com.fiap.pagamentoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringDocConfigurationsTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testCustomOpenAPIBeanCreation() {
        OpenAPI openAPI = applicationContext.getBean(OpenAPI.class);
        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Tech Challenge", info.getTitle());
        assertEquals("Trabalho final FIAP/Alura - API de pagamentos", info.getDescription());
        Contact contact = info.getContact();
        assertNotNull(contact);
        assertEquals("Time", contact.getName());
    }
}