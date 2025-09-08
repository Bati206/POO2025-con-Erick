package maintotito;

public class Jugador {
    
    private String nombre;
    private char simbolo;
    private int partidasGanadas;
    
    public Jugador(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.partidasGanadas = 0;
    }
    
    public void gano() {
        this.partidasGanadas = partidasGanadas + 1;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public char getSimbolo() {
        return this.simbolo;
    }
    
    public int getPartidasGanadas() {
        return this.partidasGanadas;
    }

    
    
}
