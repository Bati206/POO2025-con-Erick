package mainfantasyfights;

public class Item {
    
    private String nombre;
    private String tipo;
    private int efecto; //Cura, daño...
    private int cantidad;
    
    //Constructor
    public Item(String nombre, String tipo, int efecto, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.efecto = efecto;
        this.cantidad = cantidad;
    }
    
    
    // Getters
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTipo() { 
        return tipo; 
    }
    
    public int getEfecto() { 
        return efecto; 
    }
    
    public int getCantidad() { 
        return cantidad; 
    }
    
    // Setter de la cantidad de items
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }
    
    
    public void usar(Combatiente objetivo) { //Aplica el efecto según el tipo de efecto del item
        switch(tipo) {
            case "pocion_vida":
                objetivo.curar(efecto);
                return;
            case "pocion_fuego":
                objetivo.recibirDano(efecto);
                return;
            case "curacion":
                objetivo.curar(efecto);
                return;
            default:
                objetivo.curar(10); //Por si las moscas
        }
    }
    
    public boolean consumir() { //Resta un item si hay más de cero y si no retorna false
        if (cantidad > 0) {
            cantidad--;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return nombre + " (x" + cantidad + ") - Efecto: " + efecto;
    }
    
    

    
}
