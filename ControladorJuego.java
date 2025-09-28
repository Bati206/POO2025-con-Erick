package mainfantasyfights;

import java.util.ArrayList;

public class ControladorJuego {
    
    private InterfazUsuario interfaz;
    private Jugador jugador;
    private boolean juegoActivo;
    
    //Constructor
    public ControladorJuego() { 
        this.juegoActivo = false;  //Esto se cambia al mostrar el mensaje inicial
    }
    
    public void iniciarJuego() { //Muestra un mensaje inicial e inicia el ciclo principal del juego
        interfaz = new InterfazUsuario();
        interfaz.mostrarMensaje("*** Bienvenido a FantasyFights ***");
        jugador = interfaz.crearJugador();
        juegoActivo = true;
        cicloPrincipal();
    }
    
    public void cicloPrincipal() { //Pregunta si quiere jugar o no 
        while (juegoActivo) {
            ejecutarBatalla();
            if (!preguntarNuevaBatalla()) {
                juegoActivo = false;
                interfaz.mostrarMensaje("¡Gracias por jugar!");
            }
        }
    }
    
    public void ejecutarBatalla() {
        Batalla batalla = new Batalla(jugador);
        interfaz.mostrarMensaje("\n¡Nueva batalla comenzada!");
        interfaz.mostrarMensaje("Te enfrentas a " + batalla.getEnemigos().size() + " enemigo(s)!");
        
        while (!batalla.verificarFinBatalla()) {
            interfaz.mostrarEstadoBatalla(batalla);
            
            // Turno del jugador
            if (batalla.getJugador().estaVivo()) {
                procesarTurno(batalla.getJugador(), batalla);
            }
            
            if (batalla.verificarFinBatalla()) {
                verificarResultadoBatalla(batalla);
                return;
            }
            
            // Turnos de enemigos
            for (Enemigo enemigo : batalla.getEnemigos()) {
                if (enemigo.estaVivo()) {
                    procesarTurno(enemigo, batalla);
                }
                if (batalla.verificarFinBatalla()) {
                    verificarResultadoBatalla(batalla);
                    return;
                }
            }
            
            batalla.incrementarTurno();
        }
        
        verificarResultadoBatalla(batalla);
    }
    
    public void procesarTurno(Combatiente combatiente, Batalla batalla) {
        interfaz.mostrarTurnoDe(combatiente);
        int opcion = interfaz.mostrarMenu();
        
        switch(opcion) {
            case 1: // Atacar
                ArrayList<Combatiente> objetivos = new ArrayList<>();
                if (combatiente == batalla.getJugador()) {
                    for (Enemigo enemigo : batalla.getEnemigos()) {
                        if (enemigo.estaVivo()) {
                            objetivos.add(enemigo);
                        }
                    }
                } else {
                    if (batalla.getJugador().estaVivo()) {
                        objetivos.add(batalla.getJugador());
                    }
                }
                
                if (!objetivos.isEmpty()) {
                    Combatiente objetivo = interfaz.seleccionarObjetivo(objetivos);
                    combatiente.atacar(objetivo);
                    interfaz.mostrarMensaje(combatiente.getNombre() + " ataca a " + objetivo.getNombre());
                }
                return;
                
            case 2: // Usar Item
                if (combatiente == batalla.getJugador()) {
                    Jugador jugadorActual = (Jugador) combatiente;
                    if (jugadorActual.tieneItems()) {
                        Item item = interfaz.seleccionarItem(jugadorActual.getInventario());
                        if (item != null) {
                            ArrayList<Combatiente> todosVivos = batalla.obtenerCombatientesVivos();
                            if (!todosVivos.isEmpty()) {
                                Combatiente objetivo = interfaz.seleccionarObjetivo(todosVivos);
                                jugadorActual.usarItem(item, objetivo);
                                interfaz.mostrarMensaje(combatiente.getNombre() + " usa " + item.getNombre());
                            }
                        }
                    } else {
                        interfaz.mostrarMensaje("No tienes items disponibles!");
                    }
                } else {
                    combatiente.usarHabilidadEspecial(batalla.getJugador());
                    interfaz.mostrarMensaje(combatiente.getNombre() + " usa su habilidad especial!");
                }
                return;
                
            case 3: // Habilidad Especial
                if (combatiente == batalla.getJugador()) {
                    Jugador jugadorActual = (Jugador) combatiente;
                    if (jugadorActual.tieneItems()) {
                        Item item = interfaz.seleccionarItem(jugadorActual.getInventario());
                        if (item != null) {
                            ArrayList<Combatiente> todosVivos = batalla.obtenerCombatientesVivos();
                            if (!todosVivos.isEmpty()) {
                                Combatiente objetivo = interfaz.seleccionarObjetivo(todosVivos);
                                jugadorActual.usarItem(item, objetivo);
                                interfaz.mostrarMensaje(combatiente.getNombre() + " usa su habilidad especial: " + item.getNombre());
                            }
                        }
                    }
                } else {
                    interfaz.mostrarMensaje(combatiente.getNombre() + " pasa su turno.");
                }
                return;
                
            case 4: // Pasar turno
                interfaz.mostrarMensaje(combatiente.getNombre() + " pasa su turno.");
                return;
                
            default:
                interfaz.mostrarMensaje("Opcion invalida. Turno perdido.");
        }
    }
    
    public void verificarResultadoBatalla(Batalla batalla) {
        interfaz.mostrarResultadoBatalla(batalla);
    }
    
    public boolean preguntarNuevaBatalla() {
        return interfaz.preguntarContinuar();
    }
    
}
