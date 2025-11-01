package com.exemplo.dominio;

import com.exemplo.dominio.portas.FreteAPI;
import com.exemplo.dominio.portas.FreteAPIMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;




class CupomTest {

    FreteAPI frete = new FreteAPIMock();
    BigDecimal valorFrete = frete.calcularFrete("89254-000", new BigDecimal("3.0"));

    {
        // bloco de inicialização (executado ao criar o objeto)
        Cupom cupom = new Cupom(new BigDecimal("20.00"), valorFrete);
    }

    @Test
    void deveAplicarDescontoSeValido() {
        Cupom cupom = new Cupom("DESC10", new BigDecimal("0.10"), LocalDate.now().plusDays(1));
        BigDecimal resultado = cupom.aplicar(new BigDecimal("100.00"), LocalDate.now());
        assertEquals(new BigDecimal("90.00"), resultado);
    }

    @Test
    void deveLancarExcecaoSeExpirado() {
        Cupom cupom = new Cupom("EXP", new BigDecimal("0.10"), LocalDate.now().minusDays(1));
        assertThrows(IllegalStateException.class, () ->
                cupom.aplicar(new BigDecimal("100.00"), LocalDate.now()));
    }
}
