package com.exemplo.dominio;

import com.exemplo.dominio.portas.FreteAPI;
import java.math.BigDecimal;

public class FreteRegra {
    private final FreteAPI freteAPI;

    public FreteRegra(FreteAPI freteAPI) {
        this.freteAPI = freteAPI;
    }

    public BigDecimal calcular(String cepDestino, BigDecimal pesoKg) {
        if (pesoKg.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Peso inválido");
        return freteAPI.cotar(cepDestino, pesoKg);
    }
}
