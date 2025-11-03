package ragatanga.model;


import java.math.BigDecimal;


public class Produto {
    private final String sku;
    private final String nome;
    private final BigDecimal preco;


    public Produto(String sku, String nome, BigDecimal preco) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
    }


    public String getSku() { return sku; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
}