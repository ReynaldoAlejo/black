package com.bytesw.blackjack;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author reynaldo
 * @Date 10/06/2022
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JugadorTest {

    Jugador jugador = Jugador.getJugador("Reynaldo");
    double monto = 400;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el Test");
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("\nFinalizando el Test");
    }

    @Test
    @Order(1)
    @DisplayName("Prueba del ingreso de saldo")
    void ingresarSaldo() {

        jugador.ingresarSaldo(400);

        assertAll(
                ()->{assertNotNull(jugador.getDinero());},
                ()->{assertEquals(400,jugador.getDinero());}
        );
    }

    @Test
    @Order(2)
    @DisplayName("Prueba del pago de apuesta")
    void pagarApuesta() {
        jugador.pagarApuesta(monto);
        assertAll(
                ()->{assertNotNull(jugador.getDinero());},
                ()->{assertEquals(0,jugador.getDinero());}
        );
    }
}