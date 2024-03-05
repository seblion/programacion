package logica.nivel;

import logica.Elemento;
import logica.fruta.Fruta;
import logica.obstaculo.Hielo;
import logica.obstaculo.Obstaculo;
import logica.personaje.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Mapa implements Serializable {
    private static final int TAMANIO_X = 16;
    private static final int TAMANIO_Y = 16;
    private Elemento[][] posicion;
    private final ArrayList<Monstruo> monstruos;
    private final ArrayList<Fruta> frutas;


    public Mapa(Elemento[][] disposicionInicial, ArrayList<Monstruo> monstruos, ArrayList<Fruta> frutas) {
        this.posicion = disposicionInicial;
        this.monstruos = monstruos;
        this.frutas = frutas;
    }

    public Elemento[][] getPosicion() {
        return posicion;
    }

    /**
     * Método para agregar al personaje al mapa
     */
    public void agregarPersonaje(Personaje personaje) {
        int x = personaje.getCoordenadaX();
        int y = personaje.getCoordenadaY();

        if (!existeUnBloque(x, y)) {
            actualizarPosicionDelPersonaje(personaje);
        }
    }

    /**
     *  Método para validar que el monstruo se mueva solo a posiciones vacías,
     *  a menos que en esta posición se encuentre una fruta o un helado.
     */
    private void validarPosicionDelMonstruo(Monstruo monstruo) throws PosicionNoValida {
        int x = monstruo.getPosibleX();
        int y = monstruo.getPosibleY();
//        if (position[y][x] instanceof Helado)
//            return;
        if (posicion[y][x] instanceof Helado || posicion[y][x] instanceof Fruta)
            return;
        if (posicion[y][x] != null)
            throw new PosicionNoValida();
    }

    /**
     * Método para validar que el helado se mueva únicamente a posiciones vacías.
     */
    public void validarPosicionDelJugador(Helado helado) throws PosicionNoValida {

        int x = helado.getPosibleX();
        int y = helado.getPosibleY();
        if (existeUnBloque(x, y)) {
//        if(x==15 || 0 == x || y==0|| y==15){
            throw new PosicionNoValida();
        }
    }

    /**
     * Método para actualizar la posición del personaje en el mapa.
     * Se lo elimina de la posición anterior y se lo coloca en la neva posición.
     */
    public void actualizarPosicionDelPersonaje(Personaje personaje) {
        int xAnterior = personaje.getCoordenadaXAnterior();
        int yAnterior = personaje.getCoordenadaYAnterior();
        int x = personaje.getCoordenadaX();
        int y = personaje.getCoordenadaY();

        // Se vacía la posición anterior
        posicion[yAnterior][xAnterior] = null;

        // Se coloca el personaje en la nueva posición
        posicion[y][x] = personaje;
    }

    /**
     * Método para actualizar las frutas del array en la nueva posición del mapa.
     */
    public void actualizarPosicionDeFrutas() {
        for (Fruta fruta: frutas) {
            int x = fruta.getCoordenadaX();
            int y = fruta.getCoordenadaY();
            posicion[y][x] = fruta;
        }
    }


    /**
     * Método para desplazar a los monstruos a una nueva posición.
     * Para cada uno de los monstruos se calculan las cordenadas posibles.
     * Si es una posición válida, se cambian las coordenadas del monstruo y se lo agrega al mapa,
     * caso contrario, se cambia la dirección del monstruo.
     */
    public void moverMonstruos() {
        for (Monstruo monstruo : monstruos) {
            //Calcular posible movimiento
            monstruo.calcularCoordenadasPosibles(monstruo.getDireccion());
            try {
                //Evaluar si es válido el movimiento y desplazarlo
                validarPosicionDelMonstruo(monstruo);
                monstruo.desplazarse(monstruo.getDireccion());
                //Actualizar posición
                agregarPersonaje(monstruo);

            } catch (PosicionNoValida e) {
                monstruo.cambiarDireccion();
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }


    public boolean existeUnBloque(int x, int y) {
        return posicion[y][x] instanceof Obstaculo;
    }

    public boolean hayFruta(int x, int y) {
        return posicion[y][x] instanceof Fruta;
    }

    public Fruta obtenerFruta(int x, int y) {
        return (Fruta) posicion[y][x];
    }

    /**
     * Método para imprimir el mapa en la consola (verificación de la lógica).
     */
    public void imprimirMapa() {
        for (int i = 0; i < TAMANIO_Y; i++) {
            for (int j = 0; j < TAMANIO_X; j++) {
                System.out.print(posicion[i][j] == null ? '-' : posicion[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Método para validar si el monstruo ha eliminado al helado
     * Se itera sobre el array de monstruos para verificar
     * si alguno de los monstruos pertenecientes al mapa elimina al helado.
     */
    public boolean MonstruosEliminan(Helado helado) {
        for (Monstruo monstruo : monstruos) {
            if (monstruoMataAHelado(helado, monstruo))
                return true;
        }
        return false;
    }

    public boolean monstruoMataAHelado(Helado helado, Monstruo monstruo) {
        return helado.getCoordenadaX() == monstruo.getCoordenadaX() && helado.getCoordenadaY() == monstruo.getCoordenadaY();
    }

    // Método para verificar si una posición en el mapa está vacía
    public boolean laPosicionEstaVacia(int x, int y) {
        return posicion[y][x] == null;
    }

    public boolean hayHielo(int x, int y) {
        return posicion[y][x] instanceof Hielo;
    }

    /**
     * Método que coloca hielo en una dirección y posición inicial proporcionada.
     * En primer lugar, se coloca un hielo en la posición inicial.
     * Se comienza un bucle en el que se actualiza la posición respecto a la dirección dada.
     * Si la posición está dentro de los límites del juego y si la posición es nula,
     * se coloca un hielo en su lugar y se repite este proceso hasta que la posición no esté vacía
     * o esté fuera de los límites del mapa.
     */
    public void ponerHielo(int direccion, int xInicial, int yInicial) {
        int nextX = xInicial;
        int nextY = yInicial;
        posicion[nextY][nextX] = new Hielo();
        while (true) {
            switch (direccion) {
                case 0: // Arriba
                    nextY--;
                    break;
                case 1: // Derecha
                    nextX++;
                    break;
                case 2: // Abajo
                    nextY++;
                    break;
                case 3: // Izquierda
                    nextX--;
                    break;
            }

            // Verificar si la siguiente posición está dentro del mapa.
            if (nextX >= 0 && nextX < TAMANIO_X && nextY >= 0 && nextY < TAMANIO_Y) {
                // Verificar si la casilla está vacía.
                if (posicion[nextY][nextX] == null) {
                    // Poner un obstáculo de hielo en la casilla vacía.
                    posicion[nextY][nextX] = new Hielo();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Método que rompe hielo en una dirección y posición inicial proporcionada.
     * En primer lugar, se vacía la posición inicial.
     * Se comienza un bucle en el que se actualiza la posición respecto a la dirección dada.
     * Si la posición está dentro de los límites del juego y si en esta se encuentra un hielo,
     * se vacía la posición y se repite este proceso hasta que en la posición no haya hielo
     * o esté fuera de los límites del mapa.
     */
    public void romperHielo(int direccion, int xInicial, int yInicial) {
        int nextX = xInicial;
        int nextY = yInicial;
        posicion[nextY][nextX] = null;
        while (true) {
            switch (direccion) {
                case 0: // Arriba
                    nextY--;
                    break;
                case 1: // Derecha
                    nextX++;
                    break;
                case 2: // Abajo
                    nextY++;
                    break;
                case 3: // Izquierda
                    nextX--;
                    break;
            }

            // Verificar si la siguiente posición está dentro del mapa
            if (nextX >= 0 && nextX < TAMANIO_X && nextY >= 0 && nextY < TAMANIO_Y) {
                // Verificar si la casilla está vacía
                if (posicion[nextY][nextX] instanceof Hielo) {
                    // Poner un obstáculo de hielo en la casilla vacía
                    posicion[nextY][nextX] = null;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public ArrayList<Monstruo> getMonstruos() {
        return monstruos;
    }

    public boolean quedanFrutas() {
        return !frutas.isEmpty();
    }

    public void eliminarFruta(int x, int y) {
        frutas.remove((Fruta) posicion[y][x]);
    }

    public boolean hayMonstruo(int x, int y) {
        return posicion[y][x] instanceof Monstruo;
    }

}
