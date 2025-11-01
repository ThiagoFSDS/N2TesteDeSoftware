package com.exemplo.dominio;

import com.exemplo.dominio.portas.FreteAPI;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class PerformanceTest {

    @Test
    void cotacaoDeFreteDeveResponderEm500ms() {
        FreteAPI api = new FreteAPI() {
            @Override
            public BigDecimal calcularFrete(String cepDestino, BigDecimal pesoTotal) {
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                return new BigDecimal("20.00");
            }

            @Override
            public BigDecimal cotar(String cepDestino, BigDecimal pesoKg) {
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                return new BigDecimal("20.00");
            }
        };

        FreteRegra regra = new FreteRegra(api);

        assertTimeout(Duration.ofMillis(500), () ->
                regra.calcular("89255-000", new BigDecimal("1.0")));
    }
}
