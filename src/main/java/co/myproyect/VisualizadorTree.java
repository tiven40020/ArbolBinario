package co.myproyect;

import javax.swing.*;
import java.awt.*;

public class VisualizadorTree<T extends Comparable<T>> extends JPanel {
    private final Arbol<T> arbol;

    public VisualizadorTree(Arbol<T> arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol != null && arbol.obtenerRaiz() != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            dibujarNodo(g2, arbol.obtenerRaiz(), getWidth() / 2, 50, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics2D g, Nodo<T> nodo, int x, int y, int offsetX) {
        if (nodo == null) return;

        int radio = 30;

        // Dibujar conexiones
        if (nodo.getHijoIzquierdo() != null) {
            int hijoX = x - offsetX;
            int hijoY = y + 80;
            g.drawLine(x, y, hijoX, hijoY);
            dibujarNodo(g, nodo.getHijoIzquierdo(), hijoX, hijoY, offsetX / 2);
        }

        if (nodo.getHijoDerecho() != null) {
            int hijoX = x + offsetX;
            int hijoY = y + 80;
            g.drawLine(x, y, hijoX, hijoY);
            dibujarNodo(g, nodo.getHijoDerecho(), hijoX, hijoY, offsetX / 2);
        }

        // Dibujar nodo (círculo)
        g.setColor(new Color(0, 153, 255));
        g.fillOval(x - radio / 2, y - radio / 2, radio, radio);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio / 2, y - radio / 2, radio, radio);

        // Dato dentro del círculo
        String texto = nodo.getDato().toString();
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(texto);
        int textHeight = fm.getAscent();
        g.drawString(texto, x - textWidth / 2, y + textHeight / 4);
    }
}
