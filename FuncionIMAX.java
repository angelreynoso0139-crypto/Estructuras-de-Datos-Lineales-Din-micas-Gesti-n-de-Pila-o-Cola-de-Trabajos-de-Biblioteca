package cine.cola;

public class FuncionIMAX extends Proyeccion {
    private String formatoAudio;

    public FuncionIMAX(String tituloPelicula, String director, int anioEstreno, String formatoAudio) {
        super(tituloPelicula, director, anioEstreno);
        this.formatoAudio = formatoAudio;
    }

    public String getFormatoAudio() {
        return formatoAudio;
    }

    public void setFormatoAudio(String formatoAudio) {
        this.formatoAudio = formatoAudio;
    }

    @Override
    public String toString() {
        return "[IMAX] " + super.toString() + " | Audio: " + formatoAudio;
    }
}