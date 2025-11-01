package com.exemplo.dominio;

import com.exemplo.dominio.adaptadores.InMemoryEstoqueRepository;
import com.exemplo.dominio.Carrinho;
import com.exemplo.dominio.Produto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarrinhoParametrizado {

    @ParameterizedTest(name = "subtotal de {1} itens de {0} deve ser {2}")
    @CsvSource({
            "100.00, 1, 100.00",
            "59.90, 3, 179.70",
            "10.00, 0, 0.00"  // quantidade zero não adiciona nada (se adicionar, teste deve falhar)
    })
    void deveCalcularSubtotalCorretamente(String preco, int qtd, String esperado) {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setSaldo("PX", 100);

        Carrinho carrinho = new Carrinho(repo);
        Produto p = new Produto("PX", "Item", new BigDecimal(preco), "Geral");

        if (qtd > 0) {
            carrinho.adicionar(p, qtd);
        }

        assertEquals(new BigDecimal(esperado), carrinho.total(LocalDate.now()));
    }
}
