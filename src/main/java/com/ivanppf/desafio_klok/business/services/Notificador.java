package com.ivanppf.desafio_klok.business.services;

public class Notificador {
    public void enviarNotificacao(String email, String mensagem) {
        System.out.println("Enviando e-mail para " + email + ": " + mensagem);
    }
}
