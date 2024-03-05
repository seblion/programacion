package presentacion.entidad;

import presentacion.controlador.PanelBadIceCream;
import logica.nivel.Nivel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends Entidad {
    private final Nivel nivel;
    private PanelBadIceCream gp;
    public Jugador(PanelBadIceCream gp, Nivel nivel) {
        this.gp = gp;
        this.nivel = nivel;
        getPlayerImage();
    }

    public void getPlayerImage() {

        try {
            arrib1 = ImageIO.read(getClass().getResourceAsStream("/player/espalda.png"));
            arrib2 = ImageIO.read(getClass().getResourceAsStream("/player/espalda2.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/player/frente.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/player/frente2.png"));
            izqu1 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda.png"));
            izqu2 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda2.png"));
            der1 = ImageIO.read(getClass().getResourceAsStream("/player/derecha.png"));
            der2 = ImageIO.read(getClass().getResourceAsStream("/player/derecha2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarImagen() {
            contadorFigura++;
            if (contadorFigura > 15) {
                if (figuraNum == 1) {
                    figuraNum = 2;
                } else if (figuraNum == 2) {
                    figuraNum = 1;
                }
                contadorFigura = 0;
            }
        }


    public void draw(Graphics2D g2) {
        BufferedImage imagen = null;

        switch (nivel.getHelado().getDireccion()) {
            case 0:
                if (figuraNum == 1) {
                    imagen = arrib1;
                }
                if (figuraNum == 2) {
                    imagen = arrib2;
                }
                break;
            case 2:
                if (figuraNum == 1) {
                    imagen = abajo1;
                }
                if (figuraNum == 2) {
                    imagen = abajo2;
                }
                break;
            case 3:
                if (figuraNum == 1) {
                    imagen = izqu1;
                }
                if (figuraNum == 2) {
                    imagen = izqu2;
                }
                break;
            case 1:
                if (figuraNum == 1) {
                    imagen = der1;
                }
                if (figuraNum == 2) {
                    imagen = der2;
                }
                break;

        }
        int x = nivel.getHelado().getCoordenadaX()* gp.tamanioCelda;
        int y = nivel.getHelado().getCoordenadaY()* gp.tamanioCelda;
        g2.drawImage(imagen,x,y, gp.tamanioCelda, gp.tamanioCelda, null);

    }

}
