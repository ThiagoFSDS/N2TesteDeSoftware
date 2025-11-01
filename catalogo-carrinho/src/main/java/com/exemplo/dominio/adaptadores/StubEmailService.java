package com.exemplo.dominio.adaptadores;

public class StubEmailService {
    public void enviarConfirmacao(String destinatario, String mensagem) {
        // Simulação: não envia de verdade, só imprime
        System.out.println("E-mail enviado para " + destinatario + ": " + mensagem);
    }
}
