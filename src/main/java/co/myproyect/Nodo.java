package co.myproyect;

public class Nodo<T extends Comparable<T>> {
    T dato;
    Nodo<T> hijoIzquierdo;
    Nodo<T> hijoDerecho;
    Nodo<T> padre;

    public Nodo(T dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.padre = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }
}
