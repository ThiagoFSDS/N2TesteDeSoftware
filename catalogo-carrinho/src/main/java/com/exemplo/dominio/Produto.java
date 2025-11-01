package com.exemplo.dominio;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final String id;
    private final String nome;
    private final BigDecimal preco;
    private final String categoria;

    public Produto(String id, String nome, BigDecimal preco, String categoria) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id inválido");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Preço inválido");
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
