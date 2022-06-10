package com.bytesw.blackjack.enums;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public enum Categoria {
    CORAZON("Corazones"),
    DIAMANTE("Diamantes"),
    TREBOL("Trebol"),
    ESPADA("Espadas");

    String nombreCategoria;

    Categoria(String suitName) {
        this.nombreCategoria = suitName;
    }

    public String toString(){
        return nombreCategoria;
    }
}
