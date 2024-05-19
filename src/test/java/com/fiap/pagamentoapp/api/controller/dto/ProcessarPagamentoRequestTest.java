package com.fiap.pagamentoapp.api.controller.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessarPagamentoRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testProcessarPagamentoRequest() {
        ProcessarPagamentoRequest request = new ProcessarPagamentoRequest();
        request.setIdPedido(1);
        request.setValor(BigDecimal.valueOf(100.0));

        Set<ConstraintViolation<ProcessarPagamentoRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testProcessarPagamentoRequest_Invalid() {
        ProcessarPagamentoRequest request = new ProcessarPagamentoRequest();

        Set<ConstraintViolation<ProcessarPagamentoRequest>> violations = validator.validate(request);
        assertEquals(2, violations.size());
    }
}
