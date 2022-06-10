package com.bytesw.blackjack;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Juego {

    //Declaramos las variables necesarias para el juego
    private Baraja baraja;
    private Baraja descartada;
    private Crupier crupier;
    private Jugador jugador;
    private Caja caja;
    private Transaccion transaccion;

    //Contadores
    public static int ganadas;
    public static int perdidas;
    public static int empates;

    //Variable para la apuesta
    private double apuesta;

    Scanner opcion = new Scanner(System.in);

    /**
     * Constructor para el Juego
     */
    public Juego(Jugador jugador, Caja caja) {

        //Instancia bajara
        baraja = new Baraja(true);
        //Instancia una baraja vacia
        descartada = new Baraja();
        //Instancia de jugador y crupier
        crupier = new Crupier();
        this.jugador = jugador;
        //Instancia decaja
        this.caja = caja;
        //Instancia de transaccion
        this.transaccion = new Transaccion(caja, jugador);

        //Bajarar para poder jugar
        baraja.barajar();

    }


    /**
     * Comienza nueva partida, se comprueba si se da blackjack y se pregunta si se desea continuar
     */
    public void comenzar() {

        System.out.println("Saldo del jugador --> " + this.jugador.getDinero());

        do {
            System.out.print("Ingrese el monto a apostar: ");
            apuesta = opcion.nextDouble();
            if ((apuesta <= 0) || (this.jugador.getDinero() < apuesta)) {
                System.out.println("ERROR, digite cantidades positivas y menores que su saldo actual");
            }
            System.out.println();
        } while ((apuesta <= 0) || (this.jugador.getDinero() < apuesta));
        //cobramos la apuesta
        transaccion.cobrar(apuesta);
        //Si esta no es la primera ronda, muestra la puntuación de los usuarios y vuelve a poner sus cartas en el mazo.
        if (ganadas > 0 || perdidas > 0 || empates > 0) {

            crupier.getMano().descartarManoDeBaraja(descartada);
            jugador.getMano().descartarManoDeBaraja(descartada);
        }

        //Verifica que la baraja tenga al menos 4 cartas para comenzar
        if (baraja.getSobrantes() < 4) {
            //recarga la baraja desde la pila de descartes si nos quedamos sin cartas
            baraja.recargarBarajaDesdeDescartadas(descartada);
        }

        //repartir una carta el crupier
        crupier.getMano().pedirCartaDeBaraja(baraja);
        crupier.getMano().pedirCartaDeBaraja(baraja);

        //repartir una carta el crupier
        jugador.getMano().pedirCartaDeBaraja(baraja);
        jugador.getMano().pedirCartaDeBaraja(baraja);


        //mostramos la carta del crupier al jugador
        crupier.mostrarPrimeraMano();

        //mostramos las cartas del jugador
        jugador.mostrarMano();

        //Comprobamos si el crupier cumple con blackjack
        if (crupier.cumpleBlackjack()) {

            crupier.mostrarMano();

            //Comprobamos si el jugador también cumple
            if (jugador.cumpleBlackjack()) {
                //End the round with a push
                System.out.println("Ambos tienen 21 - Empate.");
                empates++;
                //Preguntar si se desea continuar
                preguntar();
            } else {
                System.out.println("BLACKJACK de " + crupier.getNombre() + ". Tu pierdes.");
                //crupier.mostrarMano();
                perdidas++;
                //Preguntar si se desea continuar
                preguntar();
            }
        }

        //Comprobamos si el jugador cumple blackjack
        if (jugador.cumpleBlackjack()) {


            System.out.println("¡BLACKJACK! ¡Tú ganas!");
            ganadas++;
            transaccion.pagar((apuesta * 3));

            //Preguntar si se desea continuar
            preguntar();
        }

        //Deja que el jugador decida qué hacer a continuación
        //pasar los mazos por si deciden acertar
        jugador.tomarDecision(baraja, descartada);

        //Comprobar si sobrepasa
        if (jugador.getMano().calcularValor() > 21) {
            System.out.println("Has pasado de 21. Pierdes");
            perdidas++;
            //Preguntar si se desea continuar
            preguntar();
        }

        //Turno del crupier
        crupier.mostrarMano();
        while (crupier.getMano().calcularValor() < 17) {
            crupier.pedir(baraja, descartada);
        }

        //comprobamos quien ganó
        if (crupier.getMano().calcularValor() > 21) {
            System.out.println("Crupier sobrepasó el 21 , tú ganas");
            transaccion.pagar(apuesta);
            ganadas++;
        } else if (crupier.getMano().calcularValor() > jugador.getMano().calcularValor()) {
            System.out.println("TÚ PIERDES!");
            perdidas++;
        } else if (jugador.getMano().calcularValor() > crupier.getMano().calcularValor()) {
            System.out.println("TÚ GANAS!");
            transaccion.pagar(apuesta);
            ganadas++;
        } else {
            System.out.println("Empate!");
            empates++;
        }

        //Preguntar si se desea continuar con el juego
        preguntar();
    }

    /**
     * Pregunta si se desea continuar jugando y se comprueba
     * si al jugador todavia le queda saldo
     */
    private void preguntar() {
        int decision = 0;
        boolean getNum = true;
        while (getNum) {

            try {
                System.out.println("Desea seguir jugando : 1) SÍ o 2) NO ");
                decision = opcion.nextInt();
                getNum = false;

            }
            //Detectamos cual quier excepcion y se sigue preguntando
            catch (Exception e) {
                System.out.println("Opcion invalida, digite el valor [1] o [2] ");
                opcion.next();
            }
            //no cerramos el escáner, porque lo necesitaremos más tarde.
            if (decision == 1) {
                if (this.jugador.getDinero() == 0) {
                    System.out.println("Se quedó sin dinero, por favor recargue.");
                    System.out.println("Un gusto jugar con usted, vuelva pronto.");
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Menu.menuDeOpciones();
                } else {
                    comenzar();
                }
            } else {
                System.out.println("Un gusto jugar con usted, vuelva pronto.");
                pause();

                Menu.menuDeOpciones();
            }
        }
    }


    /**
     * Pausa el juego por un corto tiempo.
     * Para ralentizar un poco el juego, lo que permite al usuario
     * leer las cartas que se reparten y ver qué está pasando
     */
    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
