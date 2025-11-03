package ragatanga.repository;

import java.util.Optional;
import ragatanga.exception.InsufficientStockException;

public interface EstoqueRepository {
    Optional<Integer> consultarQuantidade(String sku);
    void reservar(String sku, int quantidade) throws InsufficientStockException;
}
