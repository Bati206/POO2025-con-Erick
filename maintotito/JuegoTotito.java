package maintotito;

import java.util.Scanner;

public class JuegoTotito {
    
    
    
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int totalPartidas;
    private java.util.Scanner scanner;
    private boolean volverAJugar;
    
    public JuegoTotito() {
        tablero = new Tablero();
        jugadorActual = this.jugador1;
        totalPartidas = 0;
        scanner = new Scanner(System.in);
        volverAJugar = true;
    }
    
    public void sesionDeJuego() {
        mostrarPresentacion();
        buclePartidas();
        mostrarResumenFinal();
    }
    
    
    public void mostrarPresentacion() {
        System.out.println("¡Programa de juego de totito!");
        System.out.println("=================================");
        System.out.println("ESte es un juego clásico de totito con las reglas tipicas.");
        System.out.println("Para empezar el juego escribe el nombre del primer jugador (que utilizará el símbolo X):");
        String nombre1 = scanner.next();
        System.out.println("Ahora escribe el nombre del segundo jugador (que utilizará el símbolo O):");
        String nombre2 = scanner.next();
        this.jugador1 = new Jugador(nombre1, 'X');
        this.jugador2 = new Jugador(nombre2, 'O');
    }
    
    
    //Cambia el valor de volverAJugar a falso si en dado caso el usuario ya no quiere volver a jugar otra partida
    private void buclePartidas() {
        while (volverAJugar) {
            jugarPartida();
            System.out.print("¿Desean jugar otra partida? (si/no): ");
            String respuesta = scanner.next().toLowerCase();
            volverAJugar = respuesta.equals("si");
        }
    }
    

    
    public int solicitarCoordenada(String tipo) {
        int coordenada;
        do {
            System.out.print("Ingresa " + tipo + " (1-3): ");
            coordenada = scanner.nextInt();
            if (coordenada < 1 || coordenada > 3) {
                System.out.println("Coordenada inválida. Debe ser entre 1 y 3.");
            }
        } while (coordenada < 1 || coordenada > 3);
        
        return coordenada;
    }
    
    
    
    public void alternarJugador() {
        //Si el jugador actual es el jugador1 entonces lo cambia al jugador2, si no es el caso entonces lo cambia a jugador1
        jugadorActual = (jugadorActual == this.jugador1) ? this.jugador2 : this.jugador1; 
    }
    
    
    public boolean verificarFinDeJuego() {
        return tablero.verificarVictoria(jugadorActual.getSimbolo()) || (tablero.estaLleno());
    }
    
    
    
    
    public void jugarPartida() {
        tablero.iniciar();
        jugadorActual = this.jugador1;
        totalPartidas = totalPartidas + 1;
        
        System.out.println("\n** Partida número " + totalPartidas + "  **");
        
        while (verificarFinDeJuego()==false) {
            System.out.println(tablero.mostrar());
            realizarMovimiento();
            if (verificarFinDeJuego()==false) {
                alternarJugador();
            }
        }
        
        
        procesarFinDePartida();
    }
    
    public void realizarMovimiento() {
        boolean movimientoValido = false;
        
        while (!movimientoValido) {
            System.out.println("Turno de " + jugadorActual.getNombre() + " (" + jugadorActual.getSimbolo() + ")");
            
            int fila = solicitarCoordenada("fila") - 1;
            int columna = solicitarCoordenada("columna") - 1;
            
            if (tablero.esCasillaValida(fila, columna)) {
                tablero.colocarSimbolo(fila, columna, jugadorActual.getSimbolo());
                movimientoValido = true;
            } else {
                System.out.println("Este movimiento no es valido, haz otro.");
            }
        }
    }
    
    
    
    
    public void procesarFinDePartida() {
        System.out.println(tablero.mostrar());
        
        if (tablero.verificarVictoria(jugadorActual.getSimbolo())) {
            System.out.println("¡" + jugadorActual.getNombre() + " ha ganado la partida!");
            jugadorActual.gano();
        } else {
            System.out.println("¡Empate! El tablero está lleno.");
        }
        
        System.out.println("\nEstadísticas actuales:");
        System.out.println(this.jugador1.getNombre() + ": " + this.jugador1.getPartidasGanadas() + " victorias");
        System.out.println(this.jugador2.getNombre() + ": " + this.jugador2.getPartidasGanadas() + " victorias");
    }
    
    
    
    public void mostrarResumenFinal() {
        System.out.println("\n******************************************");
        System.out.println("         Resumen final del juego");
        System.out.println("Total de partidas jugadas: " + totalPartidas);
        System.out.println(this.jugador1.getNombre() + " con el símbolo " + this.jugador1.getSimbolo() + " ha ganado " + this.jugador1.getPartidasGanadas() + " partidas");
        System.out.println(this.jugador2.getNombre() + " con el símbolo " + this.jugador2.getSimbolo() + " ha ganado " + this.jugador2.getPartidasGanadas() + " partidas");
        
        if (this.jugador1.getPartidasGanadas() > this.jugador2.getPartidasGanadas()) {
            System.out.println("¡" + this.jugador1.getNombre() + " ganó más partidas en total!");
        } else if (this.jugador2.getPartidasGanadas() > this.jugador1.getPartidasGanadas()) {
            System.out.println("¡" + this.jugador2.getNombre() + " ganó más partidas en total!");
        } else {
            System.out.println("Nadie ganó más partidas en total, entonces se declara un empate.");
        }
    }
    
    
    
}
