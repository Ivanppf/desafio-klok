package com.ivanppf.desafio_klok.model.entities.enums;

public enum TipoCliente {
    COMUM(0),
    VIP(10);

    private final int valor;

    TipoCliente(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
