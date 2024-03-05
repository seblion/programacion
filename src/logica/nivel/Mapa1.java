package logica.nivel;

import logica.Elemento;
import logica.fruta.*;
import logica.obstaculo.*;
import logica.personaje.Monstruo;
import logica.personaje.Vaca;

import java.util.ArrayList;

public class Mapa1 extends Mapa {

    private static final Obstaculo X = new BloqueEstatico(); //Bloques estaticos y bordes
    private static final Hielo H = new Hielo(); //Cubos de hielo
    private static final Obstaculo o = null; //Area libre

    // Constructor que inicializa Mapa1 con la disposición predeterminada de elementos, monstruos y frutas.
    // El mapa se representa como una matriz bidimensional de elementos.
    public Mapa1() {
        super(new Elemento[][]{
                {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                {X, o, o, X, H, H, H, H, H, H, X, o, X, o, o, X},
                {X, o, o, o, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, H, H, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                {X, o, H, o, o, o, o, o, o, o, o, o, o, o, o, X},
                {X, o, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
        }, new ArrayList<Monstruo>() {{
            add(new Vaca(2,8));
            add(new Vaca(7,11));
            add(new Vaca(13,11));

        }}, new ArrayList<Fruta>() {{
            add(new Sandia(2,8));
            add(new Sandia(9,11));
            add(new Sandia(9,12));
            add(new Limon(9,13));
        }});
    }
}

//todo: implementar coordenadas de la fruta
