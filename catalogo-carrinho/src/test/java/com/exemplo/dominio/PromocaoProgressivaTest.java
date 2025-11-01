package com.exemplo.dominio;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromocaoProgressivaTest {

    @ParameterizedTest
    @CsvSource({
            "2,0.00",
            "3,0.05",
            "6,0.10",
            "11,0.15"
    })
    void deveCalcularDescontoProgressivo(int qtd, double esperado) {
        PromocaoProgressiva promo = new PromocaoProgressiva();
        BigDecimal resultado = promo.descontoPercentual(qtd);
        assertEquals(new BigDecimal(String.format("%.2f", esperado)), resultado);
    }
}
