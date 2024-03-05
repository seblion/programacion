package logica.fruta;

import logica.Elemento;

import java.io.Serializable;

public class Fruta extends Elemento implements Serializable {
    private int coordenadaX;
    private int coordenadaY;
    private int puntaje;

    public Fruta(int puntaje, int coordenadaX, int coordenadaY) {
        this.puntaje = puntaje;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    @Override
    public String toString() {
        return "f";
    }
    public int getPuntaje() {
        return puntaje;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }
}
