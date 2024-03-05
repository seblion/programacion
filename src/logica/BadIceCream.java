package logica;

import datos.AlmacenamientoNivel;
import logica.nivel.Nivel;
import logica.nivel.Nivel1;
import logica.nivel.Nivel2;
import presentacion.controlador.ManejadorTeclas;

import java.io.IOException;

public class BadIceCream implements Runnable {

    ManejadorTeclas keyH;
    Thread hiloJuego;
    private final Nivel nivel;
    private int FPS = 7;



    public BadIceCream(int nDeNivel, ManejadorTeclas keyH) throws IOException, ClassNotFoundException {
        this.keyH = keyH;


        Nivel nuevoNivel = null;
        switch (nDeNivel) {
            case 1:
                nuevoNivel = new Nivel1();
                break;
            case 2:
                nuevoNivel = new Nivel2();
                break;
            case -1:
                nuevoNivel = AlmacenamientoNivel.obtenerNivelGuardado();
                break;
            default:
                nuevoNivel = new Nivel1();
                break;
        }

        this.nivel = nuevoNivel;
    }



    public void empezarHiloDelJuego() {
        hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    /**
     * El siguiente método se encarga de actualizar el nivel y actualizar el jugador en bucle
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // FPS = frames per second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (hiloJuego != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                actualizarEstadoDelJugador();
                nivel.actualizarNivel();

                if (nivel.seHaFinalizadoElJuego()){
                    return;
                }
                delta--;
            }
        }
    }

    /**
     * El siguiente método se encarga de actualizar el jugador del nivel
     * tomando en cuenta la tecla que se ha presionado
     */
    private void actualizarEstadoDelJugador() {

        if (keyH.arribaPres || keyH.abajoPres
                || keyH.izquierdaPres || keyH.derechaPres || keyH.espacioPres) {

            if (keyH.arribaPres) {
                nivel.actualizarJugador(0);

            } else if (keyH.abajoPres) {
                nivel.actualizarJugador(2);

            } else if (keyH.izquierdaPres) {
                nivel.actualizarJugador(3);

            } else if (keyH.derechaPres) {
                nivel.actualizarJugador(1);

            } else if (keyH.espacioPres) {
                nivel.actualizarJugador(4);

            }

        }
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void cerrarNivel(){
       this.nivel.terminarJuego();
    }

}
