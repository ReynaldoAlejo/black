package com.bytesw.blackjack;

import com.bytesw.blackjack.enums.Categoria;
import com.bytesw.blackjack.enums.Valor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Baraja {

    private ArrayList<Carta> baraja;

    public Baraja() {
        baraja = new ArrayList<Carta>();
    }

    public Baraja(Baraja c) {
        Collections.copy(this.baraja, c.getCartas());
    }

    public Baraja(boolean hacerBarja) {
        baraja = new ArrayList<Carta>();
        if (hacerBarja) {
            //Go through all the suits
            for (Categoria suit : Categoria.values()) {
                //Go through all the ranks
                for (Valor valor : Valor.values()) {
                    //add a new card containing each iterations suit and rank
                    baraja.add(new Carta(suit, valor));
                }
            }
        }
    }

    public void addCard(Carta carta) {
        baraja.add(carta);
    }

    public void addCards(ArrayList<Carta> cartas) {
        baraja.addAll(cartas);
    }

    public String toString() {
        //A string to hold everything we're going to return
        String barajaDeCartas = "";

        for (Carta carta : baraja) {
            barajaDeCartas += carta;
            barajaDeCartas += "\n";
        }
        return barajaDeCartas;
    }

    public void barajar() {
        Collections.shuffle(baraja, new Random());
    }

    public Carta pedirCarta() {

        //Take a copy of the first card from the baraja
        Carta cartaTomada = new Carta(baraja.get(0));
        //Remove the card from the baraja
        baraja.remove(0);
        //Give the card back
        return cartaTomada;

    }

    public boolean verificarCartas() {
        if (baraja.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getSobrantes() {
        return baraja.size();
    }

    public ArrayList<Carta> getCartas() {
        return baraja;
    }

    public void vaciarBaraja() {
        baraja.clear();
    }

    public void recargarBarajaDesdeDescartadas(Baraja descartada) {
        this.addCards(descartada.getCartas());
        this.barajar();
        descartada.vaciarBaraja();
        System.out.println("Se quedó sin cartas, creando nuevas barajas a partir de la pila de descartes y barajando barajas.\n");
    }
}
