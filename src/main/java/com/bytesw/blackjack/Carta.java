package com.bytesw.blackjack;


import com.bytesw.blackjack.enums.Categoria;
import com.bytesw.blackjack.enums.Valor;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Carta implements Comparable<Carta> {
    private Categoria categoria;
    private Valor valorCarta;

    public Carta(Categoria categoria, Valor valor) {
        this.categoria = categoria;
        this.valorCarta = valor;
    }

    public Carta(Carta carta) {
        this.categoria = carta.getCategoria();
        this.valorCarta = carta.getValorCarta();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Valor getValorCarta() {
        return valorCarta;
    }

    public int getValor() {
        return valorCarta.valor;
    }

    @Override
    public String toString() {
        return ("["+valorCarta+" de "+ categoria + "] ("+this.getValor()+")");
    }

    @Override
    public int compareTo(Carta c) {
        //if this card is greater than the other card
        if (this.getValor() > c.getValor()) {
            return 1;
        } else if (this.getValor() < c.getValor()) {
            return -1;
        } else {
            return 0;
        }
    }
}
