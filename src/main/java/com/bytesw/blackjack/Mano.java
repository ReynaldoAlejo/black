package com.bytesw.blackjack;

import java.util.ArrayList;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Mano {
    private ArrayList<Carta> mano;
    public Mano() {
        mano = new ArrayList<Carta>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void pedirCartaDeBaraja(Baraja baraja) {

        mano.add(baraja.pedirCarta());
    }

    public void descartarManoDeBaraja(Baraja BarajaDescartada){

        //copia cartas de la mano a la baraja de descarte
        BarajaDescartada.addCards(mano);

        //limpiar mano
        mano.clear();

    }

    public String toString(){
        //Cadena que se mostrara
        String manoCartas = "";
        //para cada carta en la mano
        for(Carta carta: mano){
            manoCartas += carta + " - ";
        }
        return manoCartas;
    }

    public int calcularValor(){

        //variable para contar el número de ases y el valor total actual
        int valor = 0;
        int totalAces = 0;

        //Para cada carta de la mano
        for(Carta carta: mano){

            valor += carta.getValor();
            if (carta.getValor() == 11){
                totalAces ++;
            }
        }
        //conveniencia automatica
        if (valor > 21 && totalAces > 0){
            while(totalAces > 0 && valor > 21){
                totalAces --;
                valor -= 10;
            }
        }
        return valor;

    }

    public Carta getCarta(int idx){
        return mano.get(idx);
    }
}
