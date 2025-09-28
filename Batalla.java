package mainfantasyfights;

import java.util.ArrayList;
import java.util.Random;


public class Batalla {
    
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private int turnoActual; //Contador de los turnos para dar el resumen del juego
    private boolean batallaTerminada;
    
    //Constructor que crea enemigos de una
    public Batalla(Jugador jugador) {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>();
        this.turnoActual = 0;
        this.batallaTerminada = false;
        crearEnemigosAleatorios();
    }
    
    // Getters
    public Jugador getJugador() { 
        return jugador; 
    }
    
    public ArrayList<Enemigo> getEnemigos() { 
        return enemigos; 
    }
    
    public int getTurnoActual() { 
        return turnoActual; 
    }
    
    public boolean batallaTerminada() { 
        return batallaTerminada; 
    }
    
    
    public boolean verificarFinBatalla() {
        if (!jugador.estaVivo() || !hayEnemigosVivos()) { //revisa si el jugador murió o si murieron los enemigos
            batallaTerminada = true;
            return true;
        }
        return false;
    }
    
    public ArrayList<Combatiente> obtenerCombatientesVivos() {
        ArrayList<Combatiente> vivos = new ArrayList<>();
        if (jugador.estaVivo()) {
            vivos.add(jugador);
        }
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                vivos.add(enemigo);
            }
        }
        return vivos;
    }
    
    public boolean hayEnemigosVivos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                return true;
            }
        }
        return false;
    }
    
    public void incrementarTurno() {
        turnoActual++;
    }
    
    private void crearEnemigosAleatorios() {
        Random random = new Random();
        int cantidadEnemigos = random.nextInt(3) + 1;
        
        for (int i = 0; i < cantidadEnemigos; i++) {
            String tipo = (random.nextBoolean()) ? "orco" : "esqueleto";
            String nombre = generarNombre(tipo, i + 1);
            Enemigo enemigo = new Enemigo(nombre, tipo);
            enemigos.add(enemigo);
        }
    }
    
    private String generarNombre(String tipo, int numero) { //Solo le pone como nombre Orco y el numero de orco que es 
        if (tipo.equals("orco")) {
            return "Orco #" + numero;
        } else {
            return "Esqueleto #" + numero;
        }
    }
    
    
    
    
    
}
