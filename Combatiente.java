package mainfantasyfights;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public abstract class Combatiente {
    
    protected String nombre;
    protected String tipo;
    protected int puntosVida;
    protected int puntosVidaMax;
    protected int poderAtaque;
    protected boolean estaVivo;
    
    
    
    public Combatiente(String nombre, String tipo) { //El constructor utiliza los métodos para que generar los atributos según el tipo de combatiente (guerrero, explorador, orco, etc.)
        this.nombre = nombre;
        this.tipo = tipo;
        this.estaVivo = true;
        definirStatsSegunTipo();
    }
    
    
    //Getters 
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTipo() { 
        return tipo; 
    }
    
    public int getPuntosVida() { 
        return puntosVida; 
    }
    
    public int getPuntosVidaMax() { 
        return puntosVidaMax; 
    }
    
    public int getPoderAtaque() { 
        return poderAtaque; 
    }
    
    //Setter de la vida (solo es necesario alterar la vida del personaje)
    public void setPuntosVida(int vida) { 
        this.puntosVida = vida;
        if (this.puntosVida <= 0) {
            this.puntosVida = 0; //Para no dejarlo con vida negativa mejor que la vida sea igual a cero
            this.estaVivo = false;
        }
    }
    
    //Asigna valores a los atributos mediante el tipo de combatiente
    protected void definirStatsSegunTipo() {
        switch(tipo) {
            case "guerrero": //Si es de tipo guerrero tiene más vida y daño pero menos objetos (esto se ve en las clases hijas porque no todos los combatientes tienen objetos)
                this.puntosVidaMax = 120;
                this.puntosVida = 120;
                this.poderAtaque = 25;
                return;
            case "explorador": 
                this.puntosVidaMax = 80;
                this.puntosVida = 80;
                this.poderAtaque = 15;
                return;
            case "orco":
                this.puntosVidaMax = 100;
                this.puntosVida = 100;
                this.poderAtaque = 20;
                return;
            case "esqueleto":
                this.puntosVidaMax = 60;
                this.puntosVida = 60;
                this.poderAtaque = 15;
                return;
            default:  //Por si por alguna razón no es ninguno de los tipos (lo que no debería pasar) da estos valores
                this.puntosVidaMax = 50;
                this.puntosVida = 50;
                this.poderAtaque = 10;
        }
    }
    
    
     @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Vida: " + puntosVida + "/" + puntosVidaMax + " - Ataque: " + poderAtaque;
    }
    
    public void atacar(Combatiente objetivo) {
        objetivo.recibirDano(this.poderAtaque);
    }
    
    public void recibirDano(int dano) {
        this.puntosVida = this.puntosVida - dano;
        if (this.puntosVida <= 0) {
            this.puntosVida = 0;
            this.estaVivo = false;
        }
    }
    
    public void curar(int cantidad) {
        this.puntosVida = this.puntosVida + cantidad;
        if (this.puntosVida > this.puntosVidaMax) {
            this.puntosVida = this.puntosVidaMax;
        }
    }
    
    public boolean estaVivo() {
        return this.estaVivo;
    }
    
    public void crearInventarioSegunTipo() { }//Lo utilizaré para agregar los items a los combatientes jugadores
    
    public abstract String mostrarMensaje(); //Para no tener que agregar System.out.println en otras clases
    
    public abstract void usarHabilidadEspecial(Combatiente objetivo); //Realizará la acción especial 
    
    
    
    
}
