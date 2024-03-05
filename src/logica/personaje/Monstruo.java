package logica.personaje;

public abstract class Monstruo extends Personaje{

    private final String nombre;
    private int direccion;

    public Monstruo(String nombre, int direccionDeInicio, int x, int y) {
        super(x,y);
        this.nombre = nombre;
        this.direccion = direccionDeInicio;
    }
    public abstract void cambiarDireccion();

    public int getDireccion() {
        return direccion;
    }

    protected void setDireccion(int direccion) {
        this.direccion = direccion;
    }


}
