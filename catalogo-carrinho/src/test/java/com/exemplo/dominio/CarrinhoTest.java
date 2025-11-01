package com.exemplo.dominio;

import com.exemplo.dominio.adaptadores.InMemoryEstoqueRepository;
import com.exemplo.dominio.Carrinho;
import com.exemplo.dominio.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    @Test
    void deveAdicionarProdutoQuandoHaEstoque() {
        com.exemplo.dominio.adaptadores.InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setSaldo("P1", 10);

        Carrinho carrinho = new Carrinho(repo);
        Produto p = new Produto("P1", "Teclado", new BigDecimal("100.00"), "Perifericos");

        carrinho.adicionar(p, 2);

        assertEquals(1, carrinho.getItens().size());
        assertEquals(2, carrinho.getItens().get(p));
        assertEquals(new BigDecimal("200.00"), carrinho.total(LocalDate.now()));
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeInvalida() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        Carrinho carrinho = new Carrinho(repo);
        Produto p = new Produto("P1", "Teclado", new BigDecimal("100.00"), "Perifericos");

        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(p, 0));
        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(p, -1));
    }

    @Test
    void deveLancarErroQuandoEstoqueInsuficiente() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setSaldo("P1", 1);

        Carrinho carrinho = new Carrinho(repo);
        Produto p = new Produto("P1", "Teclado", new BigDecimal("100.00"), "Perifericos");

        assertThrows(RuntimeException.class, () -> carrinho.adicionar(p, 2));
    }

    @Test
    void deveRemoverProdutoELiberarEstoque() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setSaldo("P1", 5);

        Carrinho carrinho = new Carrinho(repo);
        Produto p = new Produto("P1", "Teclado", new BigDecimal("100.00"), "Perifericos");

        carrinho.adicionar(p, 3);
        carrinho.remover("P1", 3);

        assertTrue(carrinho.getItens().isEmpty());
        // total deve ser zero
        assertEquals(new BigDecimal("0.00"), carrinho.total(LocalDate.now()));
    }
}
