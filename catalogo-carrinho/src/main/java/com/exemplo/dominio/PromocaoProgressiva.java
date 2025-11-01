package com.exemplo.dominio;

import java.math.BigDecimal;

public class PromocaoProgressiva {
    public BigDecimal descontoPercentual(int quantidadeTotal) {
        if (quantidadeTotal >= 11) return new BigDecimal("0.15");
        if (quantidadeTotal >= 6) return new BigDecimal("0.10");
        if (quantidadeTotal >= 3) return new BigDecimal("0.05");
        return BigDecimal.ZERO;
    }
}
