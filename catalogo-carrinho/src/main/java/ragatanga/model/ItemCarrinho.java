package ragatanga.model;


public class ItemCarrinho {
    private final String sku;
    private final int quantidade;


    public ItemCarrinho(String sku, int quantidade) {
        this.sku = sku;
        this.quantidade = quantidade;
    }


    public String getSku() { return sku; }
    public int getQuantidade() { return quantidade; }
}