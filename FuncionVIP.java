package cine.cola;

public class FuncionVIP extends Proyeccion {
    private int numeroAsiento;

    public FuncionVIP(String tituloPelicula, String director, int anioEstreno, int numeroAsiento) {
        super(tituloPelicula, director, anioEstreno);
        this.numeroAsiento = numeroAsiento;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public String toString() {
        return "[VIP] " + super.toString() + " | Asiento VIP: " + numeroAsiento;
    }
}