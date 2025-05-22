package com.ivanppf.desafio_klok.business.services;

import org.springframework.stereotype.Component;

@Component
public class Notificador {
    public void enviarNotificacao(String email, String mensagem) {
        System.out.println("Enviando e-mail para " + email + ": " + mensagem);
    }
}
