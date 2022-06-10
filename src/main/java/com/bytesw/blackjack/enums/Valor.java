package com.bytesw.blackjack.enums;

/**
 * @author reynaldo
 * @Date 9/06/2022
 */
public enum Valor {
    ACE("Ace", 11),
    DOS("Dos", 2),
    TRES("Tres", 3),
    CUATRO("Cuatro",4),
    CINCO("Cinco",5),
    SEIS("Seis",6),
    SIETE("Siete",7),
    OCHO("Ocho",8),
    NUEVE("Nuevo",9),
    DIEZ("Diez",10),
    J("J",10),
    Q("Q",10),
    K("K",10);

    public String NombreValor;
    public int valor;

    //constructor for Enum, each Rank has a name and a value
    Valor(String NombreValor, int valor){
        this.NombreValor = NombreValor;
        this.valor = valor;
    }

    public String toString(){
        return NombreValor;
    }
}
