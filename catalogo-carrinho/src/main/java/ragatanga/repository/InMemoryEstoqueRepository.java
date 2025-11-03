package ragatanga.repository;


import ragatanga.exception.InsufficientStockException;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class InMemoryEstoqueRepository {
    private final Map<String, Integer> armazenamento = new HashMap<>();


    public void setQuantidade(String sku, int quantidade) { armazenamento.put(sku, quantidade); }


    @Override
    public Optional<Integer> consultarQuantidade(String sku) {
        return Optional.ofNullable(armazenamento.get(sku));
    }


    @Override
    public void reservar(String sku, int quantidade) {
        int atual = armazenamento.getOrDefault(sku, 0);
        if (quantidade > atual) throw new InsufficientStockException("Estoque insuficiente para " + sku);
        armazenamento.put(sku, atual - quantidade);
    }
}