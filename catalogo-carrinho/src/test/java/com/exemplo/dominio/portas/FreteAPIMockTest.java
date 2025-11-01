package com.exemplo.dominio.portas;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreteAPIMockTest {

    @Test
    void deveCalcularFreteAte5kg() {
        FreteAPI frete = new FreteAPIMock();
        BigDecimal valor = frete.calcularFrete("89254-000", new BigDecimal("3.0"));
        assertEquals(new BigDecimal("20.00"), valor);
    }

    @Test
    void deveCalcularFreteAcimaDe5kg() {
        FreteAPI frete = new FreteAPIMock();
        BigDecimal valor = frete.calcularFrete("89254-000", new BigDecimal("10.0"));
        assertEquals(new BigDecimal("50.00"), valor);
    }
}
