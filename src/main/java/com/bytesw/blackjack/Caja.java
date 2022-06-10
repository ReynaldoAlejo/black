package com.bytesw.blackjack;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public class Caja {

    private final double MONTO_BASE = 10000;
    public  static Caja caja;
    private double ganancia;

    private Caja() {
        this.ganancia = MONTO_BASE;
    }

    public static Caja getCaja() {
        if (caja == null) {
            caja = new Caja();
        }
        return caja;
    }

    public void pagarApuesta (double monto) {
        this.ganancia = this.ganancia - monto;
    }

    public void recibirPago (double monto) {
        this.ganancia = this.ganancia + monto;
    }

    public double mostrarCaja() {
        return this.ganancia;
    }

}
