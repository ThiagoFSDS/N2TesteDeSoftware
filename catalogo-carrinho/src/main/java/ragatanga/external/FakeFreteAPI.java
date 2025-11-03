package ragatanga.external;


import java.math.BigDecimal;


public class FakeFreteAPI implements FreteAPI {
    @Override
    public BigDecimal calcularFrete(String cep, double peso) {
// Simples: R$10 + R$1 por 500g
        double base = 10.0;
        double extra = Math.ceil(peso / 0.5) * 1.0;
        return BigDecimal.valueOf(base + extra);
    }
}