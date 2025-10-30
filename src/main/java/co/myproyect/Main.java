package co.myproyect;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Visualizador de Árbol Binario");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(1000, 700);
            ventana.add(new PanelArbol());
            ventana.setVisible(true);
        });
    }
}
