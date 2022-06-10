package com.bytesw.blackjack;

import com.bytesw.blackjack.abstractas.Persona;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Crupier extends Persona {
    /**
     * Crea al Crupier
     */
    public Crupier(){

        //Name the dealer "Dealer"
        super.setNombre("Crupier Mario");

    }

    /**
     * Imprime la primera mano del crupier
     */
    public void mostrarPrimeraMano(){
        System.out.println("La mano del crupier es la siguiente: ");
        System.out.println(super.getMano().getCarta(0));
    }
}
