package logica.personaje;

public class Vaca extends Monstruo {

    public Vaca(int x, int y) {
        //TODO: Comportamiento de la vaca
        super("vaca", 1, x, y);
    }

    /**
     * La vaca cambia su dirección tomando en cuenta el modo de desplazamiento de los personajes
     * Cambia de dirección en sentido horario (arriba, derecha, abaja, izquierda,...)
     */
    public void cambiarDireccion() {
        // Sumar 1 al número
        int direccion = this.getDireccion(); // Obtiene la dirección actual de la vaca
        ++direccion; // Incrementa la dirección

        // Si la dirección alcanza 4, se reinicia a 0
        if (direccion == 4) {
            direccion = 0;
        }
        // Establece la nueva dirección de la vaca
        this.setDireccion(direccion);

    }



    @Override
    public String toString() {
        return "Vaca";
    }
}
