package com.ivanppf.desafio_klok.business.services.Pedido;

import org.springframework.stereotype.Component;

@Component
public class NotificadorImpl implements Notificador {
    @Override
    public void enviarNotificacao(String email, String mensagem) {
        System.out.println("Enviando e-mail para " + email + ": " + mensagem);
    }
}
