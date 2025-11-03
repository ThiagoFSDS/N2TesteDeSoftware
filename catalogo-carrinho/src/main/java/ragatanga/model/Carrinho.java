package ragatanga.model;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Carrinho {
    private final Map<String, Integer> items = new HashMap<>();


    public void adicionar(String sku, int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("quantidade deve ser > 0");
        items.merge(sku, quantidade, Integer::sum);
    }


    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }


    public void limpar() { items.clear(); }
}