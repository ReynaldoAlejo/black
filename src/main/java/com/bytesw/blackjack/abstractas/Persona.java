package com.bytesw.blackjack.abstractas;

import com.bytesw.blackjack.Baraja;
import com.bytesw.blackjack.Juego;
import com.bytesw.blackjack.Mano;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public abstract class Persona {
    //hand holds the Persons active playing cards
    private Mano mano;
    //their name will be either Player or Dealer, this String just holds that info
    private String nombre;

    public Persona(){
        //Give them a Hand and a name
        this.mano = new Mano();
        this.nombre = "";
    }

    public Mano getMano(){
        return this.mano;
    }
    public void setMano(Mano mano){
        this.mano = mano;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void mostrarMano(){
        System.out.println(this.nombre + " su mano es la siguiente:");
        System.out.println(this.mano + " Valorado en: " + this.mano.calcularValor());
    }

    public void pedir(Baraja baraja, Baraja descartada){

        //If there's no cards left in the deck
        if (!baraja.verificarCartas()) {
            //reload the deck from the discard pile
            baraja.recargarBarajaDesdeDescartadas(descartada);
        }
        //Se toma carta de la baraja
        this.mano.pedirCartaDeBaraja(baraja);
        System.out.println(this.nombre + " recibe carta");
        //Imprimir mano
        this.mostrarMano();
        //pausamos un momento
        Juego.pause();

    }

    public boolean cumpleBlackjack(){
        if(this.getMano().calcularValor() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
