package presentacion.ventana;

import presentacion.controlador.PanelBadIceCream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaResultado extends JFrame {

    public VentanaResultado(PanelBadIceCream gp, boolean ganado) {
        setSize(800, 625);
        setTitle("Resultado");
        setLocationRelativeTo(null);

        JLabel mensajeLabel = new JLabel();
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36)); // Cambio de fuente a Comic Sans MS y tamaño aumentado a 36
        mensajeLabel.setForeground(Color.WHITE); // Cambio de color de texto a blanco

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel textoEncimaImagen = new JLabel("Ganaste"); // Agregar texto encima de la imagen
        textoEncimaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        textoEncimaImagen.setForeground(Color.BLUE); // Cambio de color de texto a blanco
        textoEncimaImagen.setFont(new Font("Comic Sans MS", Font.BOLD, 50)); // Cambio de fuente a Comic Sans MS y tamaño aumentado a 50

        JLabel textoEncimaImagen2 = new JLabel("Perdiste"); // Agregar texto encima de la imagen
        textoEncimaImagen2.setHorizontalAlignment(SwingConstants.CENTER);
        textoEncimaImagen2.setForeground(Color.RED); // Cambio de color de texto a blanco
        textoEncimaImagen2.setFont(new Font("Comic Sans MS", Font.BOLD, 50)); // Cambio de fuente a Comic Sans MS y tamaño aumentado a 50

        if (ganado) {
            mensajeLabel.setText("¡Ganaste!");
            ImageIcon image = new ImageIcon("res/ventana/ganaste.jpg");
            JLabel etiquetaImagen = new JLabel();
            etiquetaImagen.setIcon(new ImageIcon(image.getImage().getScaledInstance(800, 625, Image.SCALE_SMOOTH)));
            panel.add(textoEncimaImagen, BorderLayout.NORTH);
            panel.add(etiquetaImagen, BorderLayout.CENTER);

        } else {
            mensajeLabel.setText("¡Perdiste!");
            ImageIcon image = new ImageIcon("res/ventana/perdiste.jpg");
            JLabel etiquetaImagen = new JLabel();
            etiquetaImagen.setIcon(new ImageIcon(image.getImage().getScaledInstance(800, 625, Image.SCALE_SMOOTH)));
            panel.add(textoEncimaImagen2, BorderLayout.NORTH);
            panel.add(etiquetaImagen, BorderLayout.CENTER);

        }

        JButton botonRegresarANiveles = new JButton("Regresar a los niveles");
        botonRegresarANiveles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana ventanaJuego= new Ventana();
                ventanaJuego.setVisible(false);
                VentanaResultado.this.dispose();
                gp.eliminarVentana();
            }
        });
        botonRegresarANiveles.setPreferredSize(new Dimension(200, 50)); // Aumentar el tamaño del botón
        panel.add(mensajeLabel, BorderLayout.SOUTH);
        panel.add(botonRegresarANiveles, BorderLayout.SOUTH);

        add(panel);
    }
}



