package presentacion.controlador;

import datos.AlmacenamientoNivel;
import logica.BadIceCream;
import presentacion.entidad.*;
import presentacion.celda.ManejadorCelda;
import presentacion.ventana.VentanaResultado;
import logica.nivel.Nivel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class PanelBadIceCream extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int tamanioOriginalCelda = 16; // 16x16 tile
    private final int escala = 3;
    private static JFrame ventanaJuego;
    private final BadIceCream badIceCream;
    private boolean detenerse = false;
    public final int tamanioCelda = tamanioOriginalCelda * escala; // 48x48 tile
    public final int cantMaxCol = 16;
    public final int cantMaxFil = 16;
    public final int anchoPantalla = tamanioCelda * cantMaxCol; // 768 pixels
    public final int alturaPantalla = tamanioCelda * cantMaxFil; // 576 pixels
    int FPS = 7;
    ManejadorCelda celdaM;
    public ManejadorTeclas keyH = new ManejadorTeclas();
    Thread hiloDibujo;
    private final Nivel nivel;
    public Jugador jugador;



    public PanelBadIceCream(int nDeNivel, JFrame ventanaJuego) throws IOException, ClassNotFoundException {
        this.setPreferredSize(new Dimension(anchoPantalla, alturaPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.ventanaJuego = ventanaJuego;

        badIceCream = new BadIceCream(nDeNivel, keyH);

        this.nivel = badIceCream.getNivel();
        this.jugador = new Jugador(this, badIceCream.getNivel());
        this.celdaM = new ManejadorCelda(this);


        // Crear el botón
        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método cuando se hace clic en el botón
//            nivel.guardar();
                detenerse = true;
                eliminarVentana();
                return;
            }
        });
        this.add(botonSalir);

        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Cambiar el layout a BorderLayout
        // Crear el botón
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método cuando se hace clic en el botón
                try {
                    AlmacenamientoNivel.guardar(nivel);
                } catch (RuntimeException | IOException ex) {
                }
                detenerse = true;
                eliminarVentana();
            }
        });
        this.add(botonGuardar);
    }

    public void eliminarVentana() {
        ventanaJuego.dispose();
    }


    public void empezarJuego() {
        hiloDibujo = new Thread(this);
        badIceCream.empezarHiloDelJuego();
        hiloDibujo.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // FPS = frames per second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (hiloDibujo != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                repaint();
                if(detenerse) {
                    badIceCream.cerrarNivel();
                    return;
                }
                if (nivel.seHaFinalizadoElJuego()){
                    new VentanaResultado(this,nivel.elJuegoSeGano()).setVisible(true);
                    return;
                }
                delta--;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        celdaM.dibujarFondo(g2);
        celdaM.dibujarObjetos(g2, nivel.getMapa().getPosicion());
        jugador.actualizarImagen();
        jugador.draw(g2);

        Font fontFutura = new Font("Futura", Font.BOLD, 30);
        g2.setFont(fontFutura);
        g2.setColor(Color.WHITE);
        g2.drawString("Puntaje: " + nivel.getPuntaje(), anchoPantalla - 190, alturaPantalla - 15);
        g2.dispose();
    }

}