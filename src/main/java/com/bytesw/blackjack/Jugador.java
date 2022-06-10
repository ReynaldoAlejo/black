package com.bytesw.blackjack;

import com.bytesw.blackjack.abstractas.Persona;

import java.util.Scanner;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Jugador extends Persona {

    private double dinero;
    public static Jugador jugador;

    Scanner entrada = new Scanner(System.in);

    private Jugador(String nombre) {
        super.setNombre(nombre);
        this.dinero = 0;
    }

    public static Jugador getJugador(String nombre) {
        if (jugador == null) {
            jugador = new Jugador(nombre);
        }
        return jugador;

    }

    public double getDinero() {
        return dinero;
    }

    public void tomarDecision(Baraja baraja, Baraja descartada) {

        //Variable que guardará la decision del jugador
        int decision = 0;
        //Variable que mantendrá el bucle hasta que tome una decision valida (1 o 2)
        boolean getNum = true;

        //bucle que correra mientras se obtiene un número
        while (getNum) {

            try {

                System.out.println("\nElige una opción : 1) Pedir carta o 2) Plantarse ");
                decision = entrada.nextInt();
                getNum = false;

            } catch (Exception e) {
                System.out.println("Opcion invalida, digite el valor [1] o [2] ");
                entrada.next();
            }

        }

        //Si decidio pedir
        if (decision == 1) {
            //pedir de la baraja
            this.pedir(baraja, descartada);
            //retorna si se tiene blackjack o soprepasa
            if (this.getMano().calcularValor() > 20) {
                return;
            } else {
                //permite decidir si piden o se paran de nuevo, usando la misma baraja
                this.tomarDecision(baraja, descartada);
            }

        } else {
            //Se tomo la decision 2, es decir, se planta
            System.out.println("Se planta.");
        }

    }

    public void ingresarSaldo(double monto) {
        this.dinero = this.dinero + monto;
    }

    public void pagarApuesta(double monto) {
        this.dinero = this.dinero - monto;
    }

}
