//package guardado;
//
//import logica.nivel.Nivel;
//
//import java.io.*;
//
//public class Guardacion {
//    public static void guardar(Nivel nivel) {
//        File archivoDeNivel = new File("almacen/Nivel_guardado.txt");
//        try (FileOutputStream flujoDeSalida = new FileOutputStream(archivoDeNivel);
//             ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida)) {
//            manejadorDeEscritura.writeObject(nivel);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Nivel getNivelGuardado() {
//        File archivoDeNivel = new File("almacen/Nivel_guardado.txt");
//        try (FileInputStream flujoDeEntrada = new FileInputStream(archivoDeNivel);
//             ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada)) {
//            return (Nivel) manejadorDeLectura.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

package datos;

import logica.nivel.Nivel;

import java.io.*;


    public class AlmacenamientoNivel {
        public static void guardar(Nivel nivel) throws IOException {
            File archivoDeNivel = new File("almacen/Nivel_guardado.txt");
            FileOutputStream flujoDeSalida=new FileOutputStream(archivoDeNivel);
            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);
            manejadorDeEscritura.writeObject(nivel);
            manejadorDeEscritura.close();
        }

        public static Nivel obtenerNivelGuardado() throws IOException, ClassNotFoundException {
            // todo: impelmentar la logica de cargar nivel almacenado
            File archivoDeNivel = new File("almacen/Nivel_guardado.txt");
            FileInputStream flujoDeEntrada=new FileInputStream(archivoDeNivel);
            ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
            Nivel nivelGuardado = (Nivel) manejadorDeLectura.readObject();
            return nivelGuardado;
        }
    }


