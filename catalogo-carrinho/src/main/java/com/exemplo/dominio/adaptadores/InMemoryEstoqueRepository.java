package com.exemplo.dominio.adaptadores;

import com.exemplo.dominio.portas.EstoqueRepository;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEstoqueRepository implements EstoqueRepository {
    private final Map<String, Integer> estoque = new HashMap<>();

    public InMemoryEstoqueRepository() {}

    public void setSaldo(String produtoId, int quantidade) {
        estoque.put(produtoId, quantidade);
    }

    @Override
    public boolean reservar(String produtoId, int quantidade) {
        int saldo = estoque.getOrDefault(produtoId, 0);
        if (saldo >= quantidade) {
            estoque.put(produtoId, saldo - quantidade);
            return true;
        }
        return false;
    }

    @Override
    public void liberar(String produtoId, int quantidade) {
        int saldo = estoque.getOrDefault(produtoId, 0);
        estoque.put(produtoId, saldo + quantidade);
    }
}
