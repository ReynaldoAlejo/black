package com.bytesw.blackjack;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author reynaldo
 * @Date 10/06/2022
 */
class CajaTest {

    Caja caja = Caja.getCaja();


    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el Test");
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("Finalizando el Test");
    }


    @Test
    @DisplayName("Prueba de la cuenta base de la caja")
    void mostrarCaja() {
        double esperado = 10000;
        assertEquals(esperado,caja.mostrarCaja());
    }

    @Test
    @DisplayName("Prueba del recibo de pago de la caja")
    void recibirPago() {
        double monto = 400;
        caja.recibirPago(monto);
        assertNotNull(caja.mostrarCaja());
        assertEquals(10400,caja.mostrarCaja());
    }

    @Test
    @DisplayName("Prueba del pago de la caja")
    void pagarApuesta() {
        double monto = 400;
        caja.pagarApuesta(monto);
        assertNotNull(caja.mostrarCaja());
        assertEquals(10000,caja.mostrarCaja());
    }


}