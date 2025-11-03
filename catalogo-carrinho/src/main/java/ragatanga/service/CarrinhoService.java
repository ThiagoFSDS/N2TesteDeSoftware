package ragatanga.service;


import ragatanga.external.FreteAPI;
import ragatanga.model.Carrinho;
import ragatanga.model.Produto;
import ragatanga.repository.EstoqueRepository;
import ragatanga.exception.CouponExpiredException;


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
     * Calcula total aplicando: soma, promocao progressiva simples (5% p/ cada item acima de 1 até 20%), cupom simples
     */
    public BigDecimal calcularTotal(Carrinho carrinho, Optional<String> cupomOpt, String cep) {
        BigDecimal soma = BigDecimal.ZERO;
        double pesoTotal = 0.0; // simplificação
        for (var e : carrinho.getItems().entrySet()) {
            Produto p = catalog.get(e.getKey());
            int qtd = e.getValue();
            soma = soma.add(p.getPreco().multiply(BigDecimal.valueOf(qtd)));
            pesoTotal += 0.5 * qtd; // cada item 500g


// promo&ccedil;&atilde;o progressiva: 5% por unidade adicional (max 20%) //
            int totalItems = carrinho.getItems().values().stream().mapToInt(Integer::intValue).sum();
            double descontoPercent = Math.min(0.20, 0.05 * Math.max(0, totalItems - 1));
            BigDecimal desconto = soma.multiply(BigDecimal.valueOf(descontoPercent));
        }
    }
}
