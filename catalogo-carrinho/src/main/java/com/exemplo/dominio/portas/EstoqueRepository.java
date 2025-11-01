package com.exemplo.dominio.portas;


public interface EstoqueRepository {
    boolean reservar(String produtoId, int quantidade);
    void liberar(String produtoId, int quantidade);
}
