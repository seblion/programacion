package logica.personaje;

import logica.nivel.Mapa;

public class Helado extends Personaje {
    private String color;
    public Helado(String color) {
        super(1,1);
        this.color = color;
    }
    /**
     * Método para recoger una fruta del mapa
     * Si el helado está en la posición de una fruta, se obtiene el puntaje de la fruta
     * y se la elimina del mapa.
     * @param mapa El mapa en el que se encuentra el helado.
     *
     * @return El puntaje de la fruta recogida, o 0 si no hay fruta en la posición actual del helado.
     */
    public int recogerFruta(Mapa mapa) {
        int x = this.getPosibleX();
        int y = this.getPosibleY();
        if(mapa.hayFruta(x,y)){
            int puntaje = mapa.obtenerFruta(x,y).getPuntaje();
            mapa.eliminarFruta(x,y);
            return puntaje;
        }
        return 0;
    }


    @Override
    public String toString() {
        return "&";
    }

    /**
     * Método para romper o poner hielo hacia la última dirección que tomó el helado.
     * Cuando la primera posición adyacente al helado en la dirección presentada está vacía se pone hielo en el mapa
     * Cuando en dicha posición hay un Hielo se rompe el hielo en el mapa
     * @param mapa El mapa en el que se encuentra el helado.
     */
    public void romperOPonerHielo(Mapa mapa) {
        int direccion = this.getDireccion();
        int x = getCoordenadaX();
        int y = getCoordenadaY();

        switch (direccion) {
            case 0: // Arriba
                --y;
                break;
            case 1: // Derecha
                ++x;
                break;
            case 2: // Abajo
                ++y;
                break;
            case 3: // Izquierda
                --x;
                break;
        }

        if(mapa.laPosicionEstaVacia(x,y)) {
            mapa.ponerHielo(direccion, x, y);
            return;
        }
        if(mapa.hayHielo(x,y)) {
            mapa.romperHielo(direccion, x, y);
            return;
        }
    }
    public int getDireccion() {
        return direccionActual;
    }

}
