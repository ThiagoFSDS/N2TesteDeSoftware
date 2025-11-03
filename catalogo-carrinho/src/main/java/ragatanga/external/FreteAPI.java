package ragatanga.external;


import java.math.BigDecimal;


public interface FreteAPI {
    BigDecimal calcularFrete(String cep, double peso);
}