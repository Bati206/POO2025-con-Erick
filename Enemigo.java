package mainfantasyfights;

import java.util.Random;

public class Enemigo extends Combatiente {
    
        private boolean esJefe; //si es o no jefe

    //Constructor
        public Enemigo(String nombre, String tipo) {
        super(nombre, tipo);
        determinarJefeAleatoriamente();
        if (esJefe) {
            aplicarMultiplicadorJefe();
        }
    }
        
        private void determinarJefeAleatoriamente() {
        Random random = new Random();
        this.esJefe = random.nextInt(100) < 20; // 20% probabilidad de ser jefe
        if (esJefe) {
            this.nombre = this.nombre + " (Jefe)";
        }
    }
    
    private void aplicarMultiplicadorJefe() {
        this.puntosVidaMax = (int)(this.puntosVidaMax * 1.5);
        this.puntosVida = this.puntosVidaMax;
        this.poderAtaque = (int)(this.poderAtaque * 1.5);
    }
    
    @Override
    public String mostrarMensaje() {
        return "Turno de " + nombre;
    }
    
    @Override
    public void usarHabilidadEspecial(Combatiente objetivo) { //Según el tipo y si es jefe o no utiliza una habilidad distinta
        switch(tipo) {
            case "orco":
                if (esJefe) {
                    objetivo.recibirDano(15);
                } else {
                    objetivo.recibirDano(8);
                }
                return;
            case "esqueleto":
                if (esJefe) {
                    int dano = 12;
                    objetivo.recibirDano(dano);
                    this.curar(dano);
                } else {
                    objetivo.recibirDano(6);
                }
                return;
            default:
                objetivo.recibirDano(5);
        }
    }
    
    // Getter
    public boolean esJefe() { 
        return esJefe; 
    }
    
    
}
