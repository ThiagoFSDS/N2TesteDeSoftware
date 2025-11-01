package com.exemplo.dominio;

import com.exemplo.dominio.portas.EstoqueRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private final EstoqueRepository estoque;
    private final Map<Produto, Integer> itens = new HashMap<>();
    private Cupom cupom;
    private final PromocaoProgressiva promocao; // adicionar atributo

    // Construtor original (compatível com quem só passa estoque)
    public Carrinho(EstoqueRepository estoque) {
        this(estoque, null); // chama o construtor com dois parâmetros
    }

    // Novo construtor (estoque + promoção) — compatível com seus testes
    public Carrinho(EstoqueRepository estoque, PromocaoProgressiva promocao) {
        this.estoque = estoque;
        this.promocao = promocao;
    }


    public void adicionar(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        boolean reservado = estoque.reservar(produto.getId(), quantidade);
        if (!reservado) {
            throw new RuntimeException("Estoque insuficiente");
        }
        itens.merge(produto, quantidade, Integer::sum);
    }

    public void remover(String produtoId, int quantidade) {
        itens.entrySet().removeIf(e -> e.getKey().getId().equals(produtoId));
        estoque.liberar(produtoId, quantidade);
    }

    public void aplicarCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public BigDecimal total(LocalDate dataCompra) {
        BigDecimal subtotal = itens.entrySet().stream()
                .map(e -> e.getKey().getPreco().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (cupom != null) {
            subtotal = cupom.aplicar(subtotal, dataCompra);
        }

        return subtotal;
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }
}
