package cine.cola;

public class FuncionTradicional extends Proyeccion {
    private int numeroSala;

    public FuncionTradicional(String tituloPelicula, String director, int anioEstreno, int numeroSala) {
        super(tituloPelicula, director, anioEstreno);
        this.numeroSala = numeroSala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    @Override
    public String toString() {
        return "[TRADICIONAL] " + super.toString() + " | Sala N.º: " + numeroSala;
    }
}