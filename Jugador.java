package mainfantasyfights;

import java.util.ArrayList;


public class Jugador extends Combatiente {
    
    private ArrayList<Item> inventario; //Lista de los objetos que tendrá el jugador
    
    //Constructor
    public Jugador(String nombre, String tipo) {
        super(nombre, tipo);
        this.inventario = new ArrayList<>();
        crearInventarioSegunTipo();
    }
    
    // Getter
    public ArrayList<Item> getInventario() { 
        return inventario; 
    }
    
    @Override
    public void crearInventarioSegunTipo() {
        if (tipo.equals("guerrero")) {
            inventario.add(new Item("Pocion de Vida Grande", "pocion_vida", 50, 2));
            inventario.add(new Item("Pocion de Fuego", "pocion_fuego", 25, 1));
        } else if (tipo.equals("explorador")) {
            inventario.add(new Item("Pocion de Vida Pequena", "pocion_vida", 30, 5));
            inventario.add(new Item("Pocion de Vida Grande", "pocion_vida", 50, 2));
            inventario.add(new Item("Pocion de Fuego", "pocion_fuego", 15, 3));
            inventario.add(new Item("Antidoto", "curacion", 20, 2));
        }
    }
    
    public void usarItem(Item item, Combatiente objetivo) { //Utiliza el item y lo resta del inventario
        if (item.getCantidad() > 0) {
            item.usar(objetivo);
            item.consumir();
        }
    }
    
    public boolean tieneItems() { //revisa si tiene items
        for (Item item : inventario) {
            if (item.getCantidad() > 0) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String mostrarMensaje() {
        return "Turno de " + nombre + " (" + tipo + ")";
    }
    
    @Override
    public void usarHabilidadEspecial(Combatiente objetivo) {
        // La habilidad especial del jugador es usar items
    }
    
    public String mostrarInventario() {
        String resultado = "Inventario de " + nombre + ":\n";
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            if (item.getCantidad() > 0) {
                resultado = resultado + (i + 1) + ". " + item.toString() + "\n";
            }
        }
        return resultado;
    }
    
}
