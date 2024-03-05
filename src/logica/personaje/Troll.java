package logica.personaje;

import java.util.Random;

public class Troll extends Monstruo {

    public Troll(int x, int y) {
        //TODO: Comportamiento
        super("Troll", 1, x, y);
    }

    /**
     * Método para cambiar la dirección de movimiento del troll de forma aleatoria.
     * La posición del troll cambia de forma aleatoria entre arriba, abajo, izquierda y derecha.
     */
    public void cambiarDireccion() {
        Random rand = new Random();
        // Generar un número aleatorio entre 0 y 3 inclusive
        int direccion = rand.nextInt(4);
        this.setDireccion(direccion);
    }



    @Override
    public String toString() {
        return "Troll";
    }
}

