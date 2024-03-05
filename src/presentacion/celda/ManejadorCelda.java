package presentacion.celda;
import logica.Elemento;
import presentacion.controlador.PanelBadIceCream;
import logica.fruta.Fruta;
import logica.obstaculo.BloqueEstatico;
import logica.obstaculo.Hielo;
import logica.personaje.Monstruo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ManejadorCelda {
    private PanelBadIceCream pg;
    private Celda[] celda;
    public ManejadorCelda(PanelBadIceCream pg){
        this.pg = pg;
        celda = new Celda[10];
        obtenerImagenDeCelda();
    }
    public void obtenerImagenDeCelda(){
        try {
            celda[0] = new Celda();
            celda[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/nieve.png"));

            celda[1] = new Celda();
            celda[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/borde.png"));

            celda[2] = new Celda();
            celda[2].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/hielo.png"));


        }catch (IOException e){
            e.printStackTrace();
        }

    }



    public void dibujarObjetos(Graphics2D g2, Elemento[][] posiciones){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < pg.cantMaxFil; i++) {
            for (int j = 0; j < pg.cantMaxCol; j++) {
                Object obj = posiciones[i][j];
                if (obj instanceof BloqueEstatico) {
                    g2.drawImage(celda[1].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);
                } else if (obj instanceof Hielo) {
                    g2.drawImage(celda[2].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);
                } else if (obj instanceof Fruta) {

                    celda[3] = new Celda();
                    try {
                        celda[3].imagen = ImageIO.read(getClass().getResourceAsStream("/objects/"+obj+".png"));
                    } catch (IOException e) {
                    }

                    g2.drawImage(celda[3].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);
                } else if (obj instanceof Monstruo) {

                    celda[4] = new Celda();
                    try {
                        celda[4].imagen = ImageIO.read(getClass().getResourceAsStream("/monster/"+obj+"Monstruo.png"));
                    } catch (IOException e) {
                    }

                    g2.drawImage(celda[4].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);
                } else {
                    g2.drawImage(celda[0].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);
                }
                x += pg.tamanioCelda;
                col++;
            }
            col = 0;
            x = 0;
            y += pg.tamanioCelda;
            row++;
        }
    }

    public void dibujarFondo(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < pg.cantMaxFil; i++) {
            for (int j = 0; j < pg.cantMaxCol; j++) {
                    g2.drawImage(celda[0].imagen, x, y, pg.tamanioCelda, pg.tamanioCelda, null);

                x += pg.tamanioCelda;
                col++;
            }
            col = 0;
            x = 0;
            y += pg.tamanioCelda;
            row++;
        }
    }
}
