package com.bytesw.blackjack;

import java.util.Scanner;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Transaccion {

    private Caja caja;
    private Jugador jugador;

    Scanner sn = new Scanner(System.in);

    public Transaccion(Caja caja, Jugador jugador) {
        this.caja = caja;
        this.jugador = jugador;
    }

    public void depositar (double monto) {
        caja.recibirPago(monto);
        jugador.ingresarSaldo(monto);
    }
    public void cobrar(double monto) {
        jugador.pagarApuesta(monto);

    }

    public void pagar(double monto) {
        caja.pagarApuesta(monto);
        jugador.ingresarSaldo(monto);
    }

    public boolean validarsaldo() {
        if (jugador.getDinero() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
