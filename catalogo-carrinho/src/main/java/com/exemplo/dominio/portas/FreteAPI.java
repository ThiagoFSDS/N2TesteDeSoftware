package com.exemplo.dominio.portas;

import java.math.BigDecimal;

public interface FreteAPI {
    BigDecimal calcularFrete(String cepDestino, BigDecimal pesoTotal);

    BigDecimal cotar(String cepDestino, BigDecimal pesoKg);
}
