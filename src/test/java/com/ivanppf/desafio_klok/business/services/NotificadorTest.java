package com.ivanppf.desafio_klok.business.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificadorTest {
    
    @Test
    void deveNotificarCliente() {
        Notificador notificador = new Notificador();
        String email = "teste@exemplo.com";
        String mensagem = "Mensagem de teste";

        notificador.enviarNotificacao(email, mensagem);
    }
}
