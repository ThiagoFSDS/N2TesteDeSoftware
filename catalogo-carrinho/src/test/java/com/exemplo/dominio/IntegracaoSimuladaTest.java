package com.exemplo.dominio;

import com.exemplo.dominio.adaptadores.InMemoryEstoqueRepository;
import com.exemplo.dominio.Carrinho;
import com.exemplo.dominio.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IntegracaoSimuladaTest {

    @Test
    void fluxoCompletoReservaCalculaTotalELibera() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setSaldo("A", 5);
        repo.setSaldo("B", 10);

        Carrinho carrinho = new Carrinho(repo);

        Produto a = new Produto("A", "Caderno", new BigDecimal("15.50"), "Papelaria");
        Produto b = new Produto("B", "Caneta", new BigDecimal("3.00"), "Papelaria");

        carrinho.adicionar(a, 2); // 31.00
        carrinho.adicionar(b, 4); // 12.00
        assertEquals(new BigDecimal("43.00"), carrinho.total(LocalDate.now()));

        // remover tudo de A
        carrinho.remover("A", 2);
        assertEquals(new BigDecimal("12.00"), carrinho.total(LocalDate.now()));
    }
}
