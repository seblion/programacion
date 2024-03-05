package logica.nivel;

import logica.personaje.*;

import java.io.Serializable;

public class Nivel implements Serializable {

    private String nombre;
    private Helado helado;
    private Mapa mapa;

    private int puntaje;

    private boolean elJuegoSeTermino;
    private boolean seHaGanadoElJuego;


    public Nivel(String nombre, Mapa mapa, Helado helado) {
        this.nombre = nombre;
        this.helado = helado;
        this.mapa = mapa;
        this.seHaGanadoElJuego = false;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Inicia actualizando la posición de las frutas y moviendo los monstruos,
     * agregando dinamismo al entorno. Luego, imprime el estado del mapa para la evaluación del helado
     * verifica la condición de victoria al agotar las frutas y detecta colisiones
     * entre los monstruos y el personaje principal. Finalmente, muestra el puntaje actual.
     */
    public void actualizarNivel() {
        // Implementación para mover monstruos y actualizar frutas
        mapa.actualizarPosicionDeFrutas();
        mapa.moverMonstruos();

        //Implementacion de la interfaz
        this.mapa.imprimirMapa();
        // Verificar si se ganó el juego
        if(!mapa.quedanFrutas()){
            System.out.println("Ganaste");
            elJuegoSeTermino = true;
            seHaGanadoElJuego = true;
        }
        // Verificar colisión del helado con los monstruos
        if (mapa.MonstruosEliminan(helado)) {
            System.out.println("Perdiste");
            elJuegoSeTermino = true;
        }
        System.out.println("Puntaje: " + puntaje);
    }

    /**
     * Actualiza el jugador, recoge la fruta y suma el puntaje, mueve el helado en la dirección
     * especificada. Si la accion es igual a "4", el helado rompe o pone hielo en el mapa según el contexto
     * del mismo. de igual forma se verifica si el helado ha sido eliminado y se procede de la forma correspondiente.
     */
    public void actualizarJugador(int accion) {

        // recolectarFruta
        puntaje += helado.recogerFruta(mapa);

        moverHelado(accion);

        // Acción especial: romper o poner hielo
        if (accion == 4) {
            helado.romperOPonerHielo(mapa);
        }

        // Verificar colisión del helado con los monstruos
        if (mapa.MonstruosEliminan(helado)) {
            elJuegoSeTermino = true;
        }
    }

    /**
     *     Método privado para mover al helado según la acción proporcionada
     *     Se calculan las coordenadas posibles del helado y se valida la posición.
     *     Si es válida se cambian las coordenadas del helado y se lo coloca en el mapa,
     *     caso contrario se lanza una excepción(el helado no se mueve).
      */
    private void moverHelado(int sPosition) {
        helado.calcularCoordenadasPosibles(sPosition);
        try {
            // Validar la posición del jugador (helado)
            mapa.validarPosicionDelJugador(helado);

            // Cambiar la posición del helado
            helado.desplazarse(sPosition);
            mapa.agregarPersonaje(helado);

        } catch (PosicionNoValida | IndexOutOfBoundsException e) {

        }
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Helado getHelado() {
        return helado;
    }

    public boolean seHaFinalizadoElJuego() {
        return elJuegoSeTermino;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean elJuegoSeGano() {
        return seHaGanadoElJuego;
    }

    public void terminarJuego() {
        this.elJuegoSeTermino = true;
    }
}


