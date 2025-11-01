package com.exemplo.dominio;

import com.exemplo.dominio.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoValido() {
        Produto p = new Produto("P1", "Teclado", new BigDecimal("199.90"), "Perifericos");
        assertEquals("P1", p.getId());
        assertEquals("Teclado", p.getNome());
        assertEquals(new BigDecimal("199.90"), p.getPreco());
        assertEquals("Perifericos", p.getCategoria());
    }

    @Test
    void naoDevePermitirPrecoZeroOuNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("P2", "Mouse", BigDecimal.ZERO, "Perifericos"));
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("P3", "Mouse", new BigDecimal("-10"), "Perifericos"));
    }

    @Test
    void naoDevePermitirIdOuNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("", "Mouse", new BigDecimal("50"), "Perifericos"));
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("P4", "", new BigDecimal("50"), "Perifericos"));
    }
}
