package ragatanga.service;

import ragatanga.external.FreteAPI;
import ragatanga.model.Carrinho;
import ragatanga.model.Produto;
import ragatanga.repository.EstoqueRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CarrinhoService {
    private final EstoqueRepository estoque;
    private final FreteAPI freteApi;
    private final PaymentGateway paymentGateway;
    private final EmailService emailService;
    private final Map<String, Produto> catalog = new HashMap<>();

    public CarrinhoService(EstoqueRepository estoque, FreteAPI freteApi,
                           PaymentGateway paymentGateway, EmailService emailService) {
        this.estoque = estoque;
        this.freteApi = freteApi;
        this.paymentGateway = paymentGateway;
        this.emailService = emailService;
    }

    public void adicionarProdutoCatalog(Produto p) {
        catalog.put(p.getSku(), p);
    }

    /**
     * Calcula total aplicando:
     * - soma dos itens
     * - promoção progressiva simples (5% p/ cada item acima de 1 até 20%)
     * - cupom simples (ex: CUPOM10 = 10% de desconto)
     * - frete calculado pela API
     */
    public BigDecimal calcularTotal(Carrinho carrinho, Optional<String> cupomOpt, String cep) {
        BigDecimal soma = BigDecimal.ZERO;
        double pesoTotal = 0.0; // simplificação

        // Soma dos itens e cálculo do peso
        for (var e : carrinho.getItems().entrySet()) {
            Produto p = catalog.get(e.getKey());
            int qtd = e.getValue();
            soma = soma.add(p.getPreco().multiply(BigDecimal.valueOf(qtd)));
            pesoTotal += 0.5 * qtd; // cada item 500g
        }

        // Promoção progressiva: 5% por unidade adicional (máx. 20%)
        int totalItems = carrinho.getItems().values().stream().mapToInt(Integer::intValue).sum();
        double descontoPercent = Math.min(0.20, 0.05 * Math.max(0, totalItems - 1));
        BigDecimal desconto = soma.multiply(BigDecimal.valueOf(descontoPercent));
        soma = soma.subtract(desconto);

        // Cupom simples (exemplo: CUPOM10 dá 10% de desconto)
        if (cupomOpt.isPresent() && cupomOpt.get().equalsIgnoreCase("CUPOM10")) {
            soma = soma.multiply(BigDecimal.valueOf(0.9));
        }

        // Frete
        BigDecimal frete = freteApi.calcularFrete(cep, pesoTotal);
        soma = soma.add(frete);

        // Retorna o valor final
        return soma;
    }
}

