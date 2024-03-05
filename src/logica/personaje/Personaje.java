package logica.personaje;

import logica.Elemento;

import java.io.Serializable;

public class Personaje extends Elemento implements Serializable {

    private static final int VELOCIDAD = 1;
    private int coordenadaX;
    private int coordenadaY;
    private int coordenadaXAnterior;
    private int coordenadaYAnterior;
    private int PosibleY;
    private int PosibleX;
    protected int direccionActual;

    public Personaje(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.direccionActual = 2;
    }


    public void desplazarse(int direccion) {
//        calcularCoordenadasPosibles(direccion);

        coordenadaXAnterior = coordenadaX;
        coordenadaYAnterior = coordenadaY;

        switch (direccion) {
            case 0: // Arriba
                coordenadaY -= VELOCIDAD;
                break;
            case 1: // Derecha
                coordenadaX += VELOCIDAD;
                break;
            case 2: // Abajo
                coordenadaY += VELOCIDAD;
                break;
            case 3: // Izquierda
                coordenadaX -= VELOCIDAD;
                break;
        }

    }

//    public abstract void romperOPonerHielo(Mapa mapa);

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public int getCoordenadaXAnterior() {
        return coordenadaXAnterior;
    }

    public int getCoordenadaYAnterior() {
        return coordenadaYAnterior;
    }

    public void calcularCoordenadasPosibles(int accion) {
        obtenerUltimaDireccion(accion);
        switch (accion) {
            case 0: // Arriba
                PosibleY = coordenadaY - VELOCIDAD;
                PosibleX = coordenadaX;
                break;
            case 1: // Derecha
                PosibleX = coordenadaX + VELOCIDAD;
                PosibleY = coordenadaY;
                break;
            case 2: // Abajo
                PosibleY = coordenadaY + VELOCIDAD;
                PosibleX = coordenadaX;
                break;
            case 3: // Izquierda
                PosibleX = coordenadaX - VELOCIDAD;
                PosibleY = coordenadaY;
                break;
        }
    }
    private void obtenerUltimaDireccion(int accion) {
        int i=accion;
        if (i == 0 || i == 1 || i==2 || i == 3 )
            direccionActual = i;
    }


    public int getPosibleY() {
        return PosibleY;
    }

    public int getPosibleX() {
        return PosibleX;
    }

}
