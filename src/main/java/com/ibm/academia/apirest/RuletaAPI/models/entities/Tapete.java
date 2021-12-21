package com.ibm.academia.apirest.RuletaAPI.models.entities;

import lombok.*;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Tapete {
    int[] numerosRuleta = new int[37];
    String[] coloresRuleta = new String[37];

    public Tapete(){
        int numero = 0;
        int posicion = 0;
        int[] numerosRuleta = new int[37];
        String[] coloresRuleta = new String[37];
        String color = "negro";

        for(posicion = 1; posicion < 37; posicion++){
            numerosRuleta[posicion] = numero;
            numero++;
        }

        for(posicion = 0; posicion <= 10; posicion++){
            coloresRuleta[posicion] = color;
            color = color.equalsIgnoreCase("rojo")?"negro":"rojo";
        }
        for(posicion = 10; posicion <= 18; posicion++){
            coloresRuleta[posicion] = color;
            color = color.equalsIgnoreCase("rojo")?"negro":"rojo";
        }
        for(posicion = 18; posicion <= 29; posicion++){
            coloresRuleta[posicion] = color;
            color = color.equalsIgnoreCase("rojo")?"negro":"rojo";
        }
        for(posicion = 29; posicion < 37; posicion++){
            coloresRuleta[posicion] = color;
            color = color.equalsIgnoreCase("rojo")?"negro":"rojo";
        }
        this.numerosRuleta=numerosRuleta;
        this.coloresRuleta=coloresRuleta;
    }

    public boolean girarRuleta(Integer numero, String color){
        int tirada;
        Random random = new Random();
        tirada = random.nextInt(37);
        if (tirada==0)
            return false;
        if (numero!=null)
            if (numero==tirada)
                return true;
        if (color!=null)
            if (this.coloresRuleta[tirada].equalsIgnoreCase(color))
                return true;

        return false;
    }
}
