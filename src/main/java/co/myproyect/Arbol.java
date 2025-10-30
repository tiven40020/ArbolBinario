package co.myproyect;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol<T extends Comparable<T>> {
    Nodo<T> raiz;

    // Constructor
    public Arbol() {
        this.raiz = null;
    }

    //estaVacio
    public boolean estaVacio() {
        return raiz == null;
    }

    //Agregar dato
    public void agregarDato(T dato) {
        if (dato == null) return;
        raiz = insertarRec(raiz, dato, null);
    }

    private Nodo<T> insertarRec(Nodo<T> actual, T dato, Nodo<T> padre) {
        if (actual == null) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.setPadre(padre);
            return nuevo;
        }
        if (dato.compareTo(actual.getDato()) < 0) {
            actual.setHijoIzquierdo(insertarRec(actual.getHijoIzquierdo(), dato, actual));
        } else if (dato.compareTo(actual.getDato()) > 0) {
            actual.setHijoDerecho(insertarRec(actual.getHijoDerecho(), dato, actual));
        }
        return actual;
    }


    //Recorrer árbol (Inorden, Postorden, Preorden)
    public void recorrerInorden() {
        recorrerInordenRec(raiz);
        System.out.println();
    }

    private void recorrerInordenRec(Nodo<T> nodo) {
        if (nodo != null) {
            recorrerInordenRec(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            recorrerInordenRec(nodo.getHijoDerecho());
        }
    }

    public void recorrerPreorden() {
        recorrerPreordenRec(raiz);
        System.out.println();
    }

    private void recorrerPreordenRec(Nodo<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            recorrerPreordenRec(nodo.getHijoIzquierdo());
            recorrerPreordenRec(nodo.getHijoDerecho());
        }
    }

    public void recorrerPostorden() {
        recorrerPostordenRec(raiz);
        System.out.println();
    }

    private void recorrerPostordenRec(Nodo<T> nodo) {
        if (nodo != null) {
            recorrerPostordenRec(nodo.getHijoIzquierdo());
            recorrerPostordenRec(nodo.getHijoDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }

    //existeDato
    public boolean existeDato(T dato) {
        if (dato == null) return false;
        return buscar(raiz, dato) != null;
    }

    private Nodo<T> buscar(Nodo<T> nodo, T dato) {
        if (nodo == null || nodo.getDato().equals(dato)) return nodo;
        if (dato.compareTo(nodo.getDato()) < 0)
            return buscar(nodo.getHijoIzquierdo(), dato);
        else
            return buscar(nodo.getHijoDerecho(), dato);
    }

    //obtenerDato (dato de la raíz)
    public T obtenerDato() {
        return (raiz != null) ? raiz.getDato() : null;
    }

    //obtenerAltura
    public int obtenerAltura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(alturaRec(nodo.getHijoIzquierdo()), alturaRec(nodo.getHijoDerecho()));
    }

    //obtenerNivel de un dato
    public int obtenerNivel(T dato) {
        if (dato == null) return 0;
        return nivelRec(raiz, dato, 1);
    }

    private int nivelRec(Nodo<T> nodo, T dato, int nivel) {
        if (nodo == null) return 0;
        if (nodo.getDato().equals(dato)) return nivel;
        int nivelIzq = nivelRec(nodo.getHijoIzquierdo(), dato, nivel + 1);
        if (nivelIzq != 0) return nivelIzq;
        return nivelRec(nodo.getHijoDerecho(), dato, nivel + 1);
    }

    //contarHojas
    public int contarHojas() {
        return contarHojasRec(raiz);
    }

    private int contarHojasRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) return 1;
        return contarHojasRec(nodo.getHijoIzquierdo()) + contarHojasRec(nodo.getHijoDerecho());
    }

    //obtenerMenor
    public T obtenerMenor() {
        Nodo<T> actual = raiz;
        if (actual == null) return null;
        while (actual.getHijoIzquierdo() != null)
            actual = actual.getHijoIzquierdo();
        return actual.getDato();
    }

    //Imprimir amplitud (por niveles)
    public String imprimirAmplitud() {
        if (raiz == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            sb.append(actual.getDato()).append(" ");
            if (actual.getHijoIzquierdo() != null) cola.add(actual.getHijoIzquierdo());
            if (actual.getHijoDerecho() != null) cola.add(actual.getHijoDerecho());
        }
        return sb.toString().trim();
    }

    //Eliminar dato (con actualización de padre)
    public void eliminarDato(T dato) {
        if (dato == null) return;
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo<T> eliminarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;

        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setHijoIzquierdo(eliminarRec(nodo.getHijoIzquierdo(), dato));
            if (nodo.getHijoIzquierdo() != null) nodo.getHijoIzquierdo().setPadre(nodo);
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), dato));
            if (nodo.getHijoDerecho() != null) nodo.getHijoDerecho().setPadre(nodo);
        } else {
            // Nodo encontrado
            if (nodo.getHijoIzquierdo() == null) {
                if (nodo.getHijoDerecho() != null) nodo.getHijoDerecho().setPadre(nodo.getPadre());
                return nodo.getHijoDerecho();
            }
            if (nodo.getHijoDerecho() == null) {
                if (nodo.getHijoIzquierdo() != null) nodo.getHijoIzquierdo().setPadre(nodo.getPadre());
                return nodo.getHijoIzquierdo();
            }

            Nodo<T> sucesor = encontrarMin(nodo.getHijoDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), sucesor.getDato()));
            if (nodo.getHijoDerecho() != null) nodo.getHijoDerecho().setPadre(nodo);
        }
        return nodo;
    }

    private Nodo<T> encontrarMin(Nodo<T> nodo) {
        while (nodo.getHijoIzquierdo() != null)
            nodo = nodo.getHijoIzquierdo();
        return nodo;
    }


    // Obtener nodo mayor
    public Nodo<T> obtenerNodoMayor() {
        Nodo<T> actual = raiz;
        if (actual == null) return null;
        while (actual.getHijoDerecho() != null)
            actual = actual.getHijoDerecho();
        return actual;
    }

    //obtenerNodoMenor
    public Nodo<T> obtenerNodoMenor() {
        return encontrarMin(raiz);
    }

    //borrar el árbol
    public void borrarArbol() {
        raiz = null;
    }

    public Nodo<T> obtenerRaiz() {
        return raiz;
    }

}
