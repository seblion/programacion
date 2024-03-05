package logica.nivel;

import logica.Elemento;
import logica.fruta.Fruta;
import logica.fruta.Limon;
import logica.fruta.Uva;
import logica.obstaculo.*;
import logica.personaje.Monstruo;
import logica.personaje.Troll;

import java.util.ArrayList;

public class Mapa2 extends Mapa {

    private static final Obstaculo X = new BloqueEstatico(); //Bloques estaticos y bordes
    private static final Hielo H = new Hielo(); //Cubos de hielo
    private static final Obstaculo o = null; //Area libre

    /**
     *     Se inicializa el mapa con la disposición predeterminada de elementos, monstruos y frutas.
     *     El mapa se presenta como una matriz bidimensional de elementos.
     */

    public Mapa2() {
        super(new Elemento[][]{
                {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                {X, o, o, o, o, o, o, o, X, o, o, o, o, o, o, X},
                {X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                {X, o, o, X, H, H, H, H, H, H, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, X, o, o, X, o, o, X},
                {X, o, o, X, o, o, X, X, X, X, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, H, H, X},
                {X, o, o, H, H, H, H, H, H, H, H, o, H, o, o, X},
                {X, o, o, o, o, o, o, o, o, o, X, o, H, o, o, X},
                {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
        }, new ArrayList<Monstruo>() {{
            add(new Troll(2,8));
            add(new Troll(14,14));
            // Añadir más monstruos si es necesario
        }}, new ArrayList<Fruta>() {{
            add(new Limon(2, 8));
            add(new Limon(2, 9));
            add(new Limon(9, 11));
            add(new Uva(9, 12));
            add(new Uva(13, 13));
            add(new Uva(14 , 5));
        }});
    }
}