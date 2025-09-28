package mainfantasyfights;

import java.util.Scanner;
import java.util.ArrayList;


public class InterfazUsuario {
    
    private Scanner scanner; //Para las interacciones con el usuario
    
    //Constructor
    public InterfazUsuario() {
        scanner = new Scanner(System.in);
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public int mostrarMenu() {
        System.out.println("\n*** OPCIONES ***"); //Muestra las opciones y retorna la elección del usuario
        System.out.println("1. Atacar");
        System.out.println("2. Usar Item");
        System.out.println("3. Usar Habilidad Especial");
        System.out.println("4. Pasar turno");
        System.out.print("Selecciona una opcion: ");
        return scanner.nextInt();
    }
    
    public void mostrarTurnoDe(Combatiente combatiente) {
        System.out.println("\n");
        System.out.println(combatiente.mostrarMensaje()); //Muestra lo que hace el combatiente en su turno
        System.out.println("");
    }
    
    public Combatiente seleccionarObjetivo(ArrayList<Combatiente> combatientes) {
        System.out.println("\n*** SELECCIONAR OBJETIVO ***");
        for (int i = 0; i < combatientes.size(); i++) {
            Combatiente c = combatientes.get(i);
            if (c.estaVivo()) {
                System.out.println((i + 1) + ". " + c.toString());
            }
        }
        System.out.print("Selecciona objetivo: ");
        int opcion = scanner.nextInt();
        
        // Valida si ingreso una opción valida de objetivo
        while (opcion < 1 || opcion > combatientes.size()) {
            System.out.print("Opción inválida. Selecciona objetivo (1-" + combatientes.size() + "): ");
            opcion = scanner.nextInt();
        }
        
        return combatientes.get(opcion - 1); //Regresa el combatiente objetivo
    }
    
    public Item seleccionarItem(ArrayList<Item> items) {
        System.out.println("\n*** SELECCIONAR ITEM ***");
        ArrayList<Item> itemsDisponibles = new ArrayList<>();
        
        for (int i = 0; i < items.size(); i++) {  //ArrayList de los items
            Item item = items.get(i);
            if (item.getCantidad() > 0) {
                itemsDisponibles.add(item);
                System.out.println((itemsDisponibles.size()) + ". " + item.toString());
            }
        }
        
        if (itemsDisponibles.isEmpty()) {
            return null;
        }
        
        System.out.print("Selecciona item: ");
        int opcion = scanner.nextInt();
        
        // Valida si es un item utilizable
        while (opcion < 1 || opcion > itemsDisponibles.size()) {
            System.out.print("Opción inválida. Selecciona item (1-" + itemsDisponibles.size() + "): ");
            opcion = scanner.nextInt();
        }
        
        return itemsDisponibles.get(opcion - 1); //Retorna el item escogido
    }
    
    
    public void mostrarEstadoBatalla(Batalla batalla) { //Retorna el turno de juego y los enemigos y el jugador vivo
        System.out.println("\n");
        System.out.println("ESTADO DE LA BATALLA - Turno " + batalla.getTurnoActual());
        System.out.println("");
        
        System.out.println("JUGADOR:");
        System.out.println("  " + batalla.getJugador().toString());
        
        System.out.println("\nENEMIGOS:");
        for (Enemigo enemigo : batalla.getEnemigos()) {
            if (enemigo.estaVivo()) {
                System.out.println("  " + enemigo.toString());
            }
        }
        System.out.println("");
    }
    
    
    public boolean preguntarContinuar() { //Para ver si quiere salir del juego o no
        System.out.println("\n¿Deseas continuar jugando?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.print("Selecciona (1 o 2): ");
        int respuesta = scanner.nextInt();
        return respuesta == 1;
    }
    
    public Jugador crearJugador() {
        System.out.println("\n*** CREACION DE JUGADOR ***");
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.next();
        
        System.out.println("\nSelecciona tu clase:"); 
        System.out.println("1. Guerrero");
        System.out.println("2. Explorador");
        System.out.print("Selecciona (1 o 2): ");
        int opcion = scanner.nextInt();
        
        String tipo = (opcion == 1) ? "guerrero" : "explorador";
        return new Jugador(nombre, tipo);
    }
    
    public void mostrarResultadoBatalla(Batalla batalla) {
        System.out.println("\n");
        if (batalla.getJugador().estaVivo()) {
            System.out.println("¡VICTORIA! Has derrotado a todos los enemigos!");
        } else {
            System.out.println("¡DERROTA! Has sido derrotado...");
        }
        System.out.println("");
    }
    
    
    
}
