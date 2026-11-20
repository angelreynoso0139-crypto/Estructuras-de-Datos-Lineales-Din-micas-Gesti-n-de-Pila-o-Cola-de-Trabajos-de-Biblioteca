package cine.cola;

import java.util.NoSuchElementException;

public class ColaCineFIFO {

    private static class Nodo {
        Proyeccion dato;
        Nodo siguiente;

        Nodo(Proyeccion dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public ColaCineFIFO() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public void offer(Proyeccion proyeccion) {
        if (proyeccion == null) {
            throw new IllegalArgumentException("No se puede encolar una función nula.");
        }
        Nodo nuevoNodo = new Nodo(proyeccion);
        if (isEmpty()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamanio++;
    }

    public Proyeccion poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía. No hay funciones por atender.");
        }
        Proyeccion atendida = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return atendida;
    }

    public Proyeccion peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía. No hay elemento al frente.");
        }
        return frente.dato;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public void vaciarCola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public String obtenerEstadoVisual() {
        if (isEmpty()) {
            return "[ COLA DEL CINE VACÍA - No hay funciones en espera ]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Funciones en cola (FIFO): ").append(tamanio).append("\n");
        sb.append("==================================================================\n");

        Nodo actual = frente;
        int turno = 1;

        while (actual != null) {
            if (actual == frente && actual == fin) {
                sb.append("▶ [FRENTE / FINAL] ");
            } else if (actual == frente) {
                sb.append("▶ [FRENTE - Turno 1] ");
            } else if (actual == fin) {
                sb.append("◀ [FINAL  - Turno ").append(turno).append("] ");
            } else {
                sb.append("   [Turno ").append(turno).append("]         ");
            }

            sb.append(actual.dato.toString()).append("\n");

            if (actual.siguiente != null) {
                sb.append("        │\n");
            }
            actual = actual.siguiente;
            turno++;
        }

        sb.append("==================================================================\n");
        return sb.toString();
    }
}