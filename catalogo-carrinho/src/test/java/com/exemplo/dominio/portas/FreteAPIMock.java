package com.exemplo.dominio.portas;

import java.math.BigDecimal;

public class FreteAPIMock implements FreteAPI {

    @Override
    public BigDecimal calcularFrete(String cepDestino, BigDecimal pesoTotal) {
        if (pesoTotal.compareTo(new BigDecimal("5.0")) <= 0) {
            return new BigDecimal("20.00");
        } else {
            return new BigDecimal("50.00");
        }
    }

    @Override
    public BigDecimal cotar(String cepDestino, BigDecimal pesoKg) {
        // Podemos apenas redirecionar para calcularFrete
        return calcularFrete(cepDestino, pesoKg);
    }
}