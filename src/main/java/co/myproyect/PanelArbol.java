package co.myproyect;

import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {

    private final Arbol<Integer> arbol;
    private final VisualizadorTree<Integer> visualizador;
    private final JTextField txtDato;
    private final JTextArea txtSalida;

    public PanelArbol() {
        setLayout(new BorderLayout());
        arbol = new Arbol<>();
        visualizador = new VisualizadorTree<>(arbol);

        // Panel superior con controles
        JPanel panelControles = new JPanel(new GridLayout(3, 4, 8, 5));
        txtDato = new JTextField();
        txtSalida = new JTextArea(5, 30);
        txtSalida.setEditable(false);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBorrar = new JButton("Borrar Árbol");
        JButton btnInorden = new JButton("Inorden");
        JButton btnPreorden = new JButton("Preorden");
        JButton btnPostorden = new JButton("Postorden");
        JButton btnContarHojas = new JButton("Contar Hojas");
        JButton btnNivel = new JButton("Nivel de Nodo");
        JButton btnMayor = new JButton("Nodo Mayor");
        JButton btnMenor = new JButton("Nodo Menor");
        JButton btnAltura = new JButton("Altura Árbol");
        JButton btnAmplitud = new JButton("Amplitud");

        panelControles.add(new JLabel("Dato:"));
        panelControles.add(txtDato);
        panelControles.add(btnAgregar);
        panelControles.add(btnEliminar);
        panelControles.add(btnBorrar);
        panelControles.add(btnInorden);
        panelControles.add(btnPreorden);
        panelControles.add(btnPostorden);
        panelControles.add(btnContarHojas);
        panelControles.add(btnNivel);
        panelControles.add(btnMayor);
        panelControles.add(btnMenor);
        panelControles.add(btnAltura);
        panelControles.add(btnAmplitud);

        add(panelControles, BorderLayout.NORTH);
        add(new JScrollPane(visualizador), BorderLayout.CENTER);
        add(new JScrollPane(txtSalida), BorderLayout.SOUTH);

        // Acciones de los botones
        btnAgregar.addActionListener(e -> {
            try {
                int dato = Integer.parseInt(txtDato.getText());
                arbol.agregarDato(dato);
                actualizar("Nodo agregado: " + dato);
            } catch (NumberFormatException ex) {
                mostrarError("Ingrese un número válido");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int dato = Integer.parseInt(txtDato.getText());
                arbol.eliminarDato(dato);
                actualizar("Nodo eliminado: " + dato);
            } catch (NumberFormatException ex) {
                mostrarError("Ingrese un número válido");
            }
        });

        btnBorrar.addActionListener(e -> {
            arbol.borrarArbol();
            actualizar("Árbol borrado.");
        });

        // Recorridos: capturamos la salida de consola
        btnInorden.addActionListener(e -> {
            String salida = capturarSalida(() -> arbol.recorrerInorden());
            mostrarTexto("Inorden: " + salida.trim());
        });

        btnPreorden.addActionListener(e -> {
            String salida = capturarSalida(() -> arbol.recorrerPreorden());
            mostrarTexto("Preorden: " + salida.trim());
        });

        btnPostorden.addActionListener(e -> {
            String salida = capturarSalida(() -> arbol.recorrerPostorden());
            mostrarTexto("Postorden: " + salida.trim());
        });

        btnContarHojas.addActionListener(e ->
                mostrarTexto("Cantidad de hojas: " + arbol.contarHojas()));

        btnNivel.addActionListener(e -> {
            try {
                int dato = Integer.parseInt(txtDato.getText());
                int nivel = arbol.obtenerNivel(dato);
                if (nivel > 0)
                    mostrarTexto("Nivel del nodo " + dato + ": " + nivel);
                else
                    mostrarError("El nodo no existe");
            } catch (NumberFormatException ex) {
                mostrarError("Ingrese un número válido");
            }
        });

        btnMayor.addActionListener(e -> {
            Nodo<Integer> mayor = arbol.obtenerNodoMayor();
            mostrarTexto(mayor != null ? "Nodo mayor: " + mayor.getDato() : "Árbol vacío");
        });

        btnMenor.addActionListener(e -> {
            Nodo<Integer> menor = arbol.obtenerNodoMenor();
            mostrarTexto(menor != null ? "Nodo menor: " + menor.getDato() : "Árbol vacío");
        });

        btnAltura.addActionListener(e ->
                mostrarTexto("Altura del árbol: " + arbol.obtenerAltura()));

        btnAmplitud.addActionListener(e ->
                mostrarTexto("Recorrido por niveles: " + arbol.imprimirAmplitud()));
    }

    private void actualizar(String mensaje) {
        visualizador.repaint();
        mostrarTexto(mensaje);
        txtDato.setText("");
    }

    private void mostrarTexto(String texto) {
        txtSalida.append(texto + "\n");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Captura la salida del System.out en un String
    private String capturarSalida(Runnable accion) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        accion.run();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

}
