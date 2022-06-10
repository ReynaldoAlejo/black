package com.bytesw.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Menu {
    public static void menuDeOpciones() {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        double monto;// Monto a recargar
        Caja caja = Caja.getCaja();
        Jugador jugador = Jugador.getJugador("Reynaldo");
        Juego juego = new Juego(jugador, caja);
        Transaccion transaccion = new Transaccion(caja, jugador);

        while (!salir) {
            System.out.println("\nBIENVENIDO A SU CONSOLA DE JUEGOS");
            System.out.println("1. Jugar BlackJack");
            System.out.println("2. Ingresar Saldo");
            System.out.println("3. Ver historial");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Salir");

            try {

                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();


                switch (opcion) {
                    case 1:
                        if (!transaccion.validarsaldo()){
                            System.out.println("No tiene saldo para apostar, recargue por favor.");
                            do {
                                System.out.print("¿Regresar al menu? Sí(1) o No(2) : ");
                                opcion = sn.nextInt();
                            } while ((opcion != 1));
                        }else {
                            System.out.println("\n*****BLACKJACK-21*****");
                            juego.comenzar();
                        }
                        break;
                    case 2:
                        System.out.println("\nRECARGAR SALDO");

                        do {
                            System.out.print("Ingrese el monto a recargar: ");
                            monto = sn.nextDouble();
                            if (monto <= 0) {
                                System.out.println("ERROR, digite cantidades positivas...");
                            }
                        } while (monto <= 0);
                        transaccion.depositar(monto);
                        System.out.println("Operacion realizada con éxito, puede jugar.");
                        Juego.pause();
                        break;
                    case 3:
                        System.out.println("\nHISTORIAL DE RESULTADOS ");
                        System.out.println("Juegos ganados por el jugador " + jugador.getNombre() + " = " + Juego.ganadas);
                        System.out.println("Juegos ganados por la casa = " + Juego.perdidas);
                        System.out.println("Juegos empatados = " + Juego.empates);

                        do {
                            System.out.print("\n¿Regresar al menu? Sí(1) o No(2) : ");
                            opcion = sn.nextInt();
                        } while ((opcion != 1));
                        break;
                    case 4:
                        System.out.println("CONSULTA DE SALDO");
                        System.out.println("Jugardor "+jugador.getNombre()+" su saldo es : "+jugador.getDinero());

                        do {
                            System.out.print("\n¿Regresar al menu? Sí(1) o No(2) : ");
                            opcion = sn.nextInt();
                        } while ((opcion != 1));
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                Juego.pause();
                sn.next();
            }
        }
    }
}
