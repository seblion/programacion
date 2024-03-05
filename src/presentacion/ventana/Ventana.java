package presentacion.ventana;
import presentacion.controlador.PanelBadIceCream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Ventana extends JFrame {
    public Ventana() {
        setSize(800, 625); // tamanio ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.getContentPane().add(panel);

        // Botón de Comenzar
        JButton botonComenzar = new JButton("COMENZAR");
        botonComenzar.setBounds(220, 530, 350, 40);
        botonComenzar.setForeground(Color.BLACK);
        botonComenzar.setOpaque(true);
        botonComenzar.setBackground(Color.WHITE);
        botonComenzar.setFont(new Font("cooper black", 0, 20));
        panel.add(botonComenzar);

        // Imagen
        ImageIcon image = new ImageIcon("res/ventana/ventana.png");
        JLabel etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(20, 10, 750, 590);
        etiquetaImagen.setIcon(new ImageIcon(image.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);


        // Agregar ActionListener al botón
        botonComenzar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo panel para los botones Iniciar Partida y Cargar Partida
                JPanel nuevoPanel = new JPanel();
                nuevoPanel.setLayout(null);
                nuevoPanel.setBackground(Color.WHITE);


                // Botón Iniciar Partida
                JButton botonIniciarPartida = new JButton("Iniciar Partida");
                botonIniciarPartida.setForeground(Color.BLACK);
                botonIniciarPartida.setFont(new Font("britannic bold", 0, 20));
                botonIniciarPartida.setBounds(280, 200, 200, 40);
                botonIniciarPartida.setHorizontalAlignment(SwingConstants.CENTER);
                nuevoPanel.add(botonIniciarPartida);

                // Agregar ActionListener al botón Iniciar Partida
                botonIniciarPartida.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel nuevoPanel2 = new JPanel();
                        nuevoPanel2.setLayout(null);

                        setContentPane(nuevoPanel2);
                        revalidate();
                        repaint();
                        setVisible(true);

                        int numNiveles = 2;  // Modifica este valor según la cantidad de niveles que tengas
                        for (int i = 1; i <= numNiveles; i++) {
                            JButton botonNivel = crearBotonNivel("Nivel " + i, 280, 200 + (i - 1) * 100, 200, 40, i);
                            nuevoPanel2.add(botonNivel);
                        }

                        //Boton regresar
                        JButton botonRegresar = new JButton("Regresar");
                        botonRegresar.setForeground(Color.BLACK);
                        botonRegresar.setFont(new Font("britannic bold", 0, 20));
                        botonRegresar.setBounds(30, 30, 190, 35);
                        nuevoPanel2.add(botonRegresar);

                        botonRegresar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setContentPane(nuevoPanel);
                                revalidate();
                                repaint();
                                setVisible(true);
                            }
                        });

                        ImageIcon image2 = new ImageIcon("res/ventana/fondo.png");
                        JLabel etiquetaImagen2 = new JLabel();
                        etiquetaImagen2.setBounds(20, 10, 750, 590);
                        etiquetaImagen2.setIcon(new ImageIcon(image2.getImage().getScaledInstance(etiquetaImagen2.getWidth(), etiquetaImagen2.getHeight(), Image.SCALE_SMOOTH)));
                        nuevoPanel2.add(etiquetaImagen2);

                        // Aquí puedes agregar la lógica para abrir una nueva ventana
                        //JOptionPane.showMessageDialog(null, "¡Ventana de juego iniciada!");
                    }
                });

                // Botón Cargar Partida
                JButton botonCargarPartida = new JButton("Cargar Partida");
                botonCargarPartida.setForeground(Color.BLACK);
                botonCargarPartida.setFont(new Font("britannic bold", 0, 20));
                botonCargarPartida.setBounds(280, 300, 200, 40);
                nuevoPanel.add(botonCargarPartida);

                // Agregar el nuevo panel al JFrame
                setContentPane(nuevoPanel);
                revalidate();
                repaint();
                setVisible(true);


                // Imagen
                ImageIcon image2 = new ImageIcon("res/ventana/fondo.png");
                JLabel etiquetaImagen2 = new JLabel();
                etiquetaImagen2.setBounds(20, 10, 750, 590);
                etiquetaImagen2.setIcon(new ImageIcon(image2.getImage().getScaledInstance(etiquetaImagen2.getWidth(), etiquetaImagen2.getHeight(), Image.SCALE_SMOOTH)));
                nuevoPanel.add(etiquetaImagen2);

                botonCargarPartida.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame ventanaJuego = new JFrame();
                        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ventanaJuego.setResizable(false);
                        ventanaJuego.setTitle("Bad Ice Cream");


                        PanelBadIceCream panelBadIceCream = null;

                        try {
                            // Puedes cargar el estado del juego guardado aquí
                            panelBadIceCream = new PanelBadIceCream(-1, ventanaJuego);
                            ventanaJuego.add(panelBadIceCream);
                            ventanaJuego.pack();
                            ventanaJuego.setLocationRelativeTo(null);
                            ventanaJuego.setVisible(true);
                            assert panelBadIceCream != null;
                            panelBadIceCream.empezarJuego();
                        } catch (ClassNotFoundException | IOException ignored) {
                            System.out.println("Nivel no encontrado");
                        }

                    }
                });
            }

        });
    }
    private JButton crearBotonNivel(String texto, int x, int y, int ancho, int alto, int nivel) {
        JButton botonNivel = new JButton(texto);
        botonNivel.setForeground(Color.BLACK);
        botonNivel.setFont(new Font("britannic bold", 0, 20));
        botonNivel.setBounds(x, y, ancho, alto);
        botonNivel.setHorizontalAlignment(SwingConstants.CENTER);

        botonNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaJuego = new JFrame();
                ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaJuego.setResizable(false);
                ventanaJuego.setTitle("Bad Ice Cream");


                PanelBadIceCream panelBadIceCream = null;

                try {
                    panelBadIceCream = new PanelBadIceCream(nivel, ventanaJuego);
                } catch (ClassNotFoundException | IOException ignored) {
                }


                ventanaJuego.add(panelBadIceCream);
                ventanaJuego.pack();
                ventanaJuego.setLocationRelativeTo(null);
                ventanaJuego.setVisible(true);

                assert panelBadIceCream != null;
                panelBadIceCream.empezarJuego();
            }
        });

        return botonNivel;
    }
}