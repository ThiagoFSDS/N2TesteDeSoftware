package com.exemplo.dominio;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import com.exemplo.dominio.adaptadores.InMemoryEstoqueRepository;

import static org.junit.jupiter.api.Assertions.*;

class ExcecoesTest {

    @Test
    void deveLancarExcecaoSeQuantidadeInvalida() {
        Carrinho carrinho = new Carrinho(new InMemoryEstoqueRepository(), new PromocaoProgressiva());
        Produto p = new Produto("P1", "Caneca", new BigDecimal("20.00"), "COZINHA");
        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(p, 0));
    }

    @Test
    void deveLancarExcecaoSeEstoqueInsuficiente() {
        InMemoryEstoqueRepository estoque = new InMemoryEstoqueRepository();
        estoque.setSaldo("P1", 1);
        Carrinho carrinho = new Carrinho(estoque, new PromocaoProgressiva());
        Produto p = new Produto("P1", "Caneca", new BigDecimal("20.00"), "COZINHA");
        assertThrows(IllegalStateException.class, () -> carrinho.adicionar(p, 2));
    }
}
