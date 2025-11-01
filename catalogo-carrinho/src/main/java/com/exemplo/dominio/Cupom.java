package com.exemplo.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

    public class Cupom {
        private final String codigo;
        private final BigDecimal percentual;
        private final LocalDate validade;

        public Cupom(String codigo, BigDecimal percentual, LocalDate validade) {
            if (percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(BigDecimal.ONE) > 0)
                throw new IllegalArgumentException("Percentual inválido");
            this.codigo = codigo;
            this.percentual = percentual;
            this.validade = validade;
        }
        public Cupom(BigDecimal valorDesconto, BigDecimal valorFrete) {
            this.codigo = "FRETE";
            // calcula o percentual baseado no valor do frete
            this.percentual = valorDesconto
                    .divide(valorFrete, 2, RoundingMode.HALF_UP);
            this.validade = LocalDate.now().plusDays(1); // apenas um exemplo de validade
        }

        public BigDecimal aplicar(BigDecimal valor, LocalDate hoje) {
            if (hoje.isAfter(validade)) throw new IllegalStateException("Cupom expirado");
            return valor.multiply(BigDecimal.ONE.subtract(percentual));
        }
    }
