package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManejadorTeclas implements KeyListener {
    public boolean arribaPres, abajoPres, izquierdaPres, derechaPres, espacioPres;
    public boolean arribaSolt, abajoSolt, izquierdaSolt, derechaSolt, espacioSolt;

    @Override
    public void keyTyped(KeyEvent e) {
        // No necesitas implementar esto, pero es requerido por la interfaz
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W && !arribaPres) {
            arribaPres = true;
            arribaSolt = false;
        }
        if (code == KeyEvent.VK_S && !abajoPres) {
            abajoPres = true;
            abajoSolt = false;
        }
        if (code == KeyEvent.VK_A && !izquierdaPres) {
            izquierdaPres = true;
            izquierdaSolt = false;
        }
        if (code == KeyEvent.VK_D && !derechaPres) {
            derechaPres = true;
            derechaSolt = false;
        }
        if (code == KeyEvent.VK_SPACE && !espacioPres) {
            espacioPres = true;
            espacioSolt = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            arribaPres = false;
            arribaSolt = true;
        }
        if (code == KeyEvent.VK_S) {
            abajoPres = false;
            abajoSolt = true;
        }
        if (code == KeyEvent.VK_A) {
            izquierdaPres = false;
            izquierdaSolt = true;
        }
        if (code == KeyEvent.VK_D) {
            derechaPres = false;
            derechaSolt = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            espacioPres = false;
            espacioSolt = true;
        }
    }
}

