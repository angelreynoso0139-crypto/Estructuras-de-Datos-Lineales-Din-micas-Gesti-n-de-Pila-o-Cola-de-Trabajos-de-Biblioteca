package cine.cola;

public class Proyeccion {
    protected String tituloPelicula;
    protected String director;
    protected int anioEstreno;

    public Proyeccion(String tituloPelicula, String director, int anioEstreno) {
        this.tituloPelicula = tituloPelicula;
        this.director = director;
        this.anioEstreno = anioEstreno;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    @Override
    public String toString() {
        return "Película: " + tituloPelicula + " | Director: " + director + " | Estreno: " + anioEstreno;
    }
}