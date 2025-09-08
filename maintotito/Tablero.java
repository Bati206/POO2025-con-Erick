package maintotito;

public class Tablero {
    
    
    private char[][] casillas;
    private final int tamano = 3;
    
    public Tablero() {
        casillas = new char[tamano][tamano];
        iniciar();
    }
    
    public void iniciar() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                casillas[i][j] = ' ';
            }
        }
    }
    
    public String mostrar() {
    String resultado = "\n  1   2   3\n";
    for (int i = 0; i < tamano; i++) {
        resultado += (i + 1) + " ";
        for (int j = 0; j < tamano; j++) {
            resultado += casillas[i][j];
            if (j < tamano - 1) {
                resultado += " | ";
            }
        }
        resultado += "\n";
        if (i < tamano - 1) {
            resultado += "  ---------\n";
        }
    }
    resultado += "\n";
    return resultado;
}
    
    
    public boolean esCasillaValida(int fila, int columna) {
        if (fila < 0 || fila >= tamano || columna < 0 || columna >= tamano) {
            return false;
        }
        return casillas[fila][columna] == ' ';
    }
    
    public void colocarSimbolo(int fila, int columna, char simbolo) {
        casillas[fila][columna] = simbolo;
    }
    
    
    
    public boolean verificarVictoria(char marca) {
        // Revisa si hay tres en fila.
        for (int i = 0; i < tamano; i++) {
            if (casillas[i][0] == marca && casillas[i][1] == marca && casillas[i][2] == marca) {
                return true;
            }
        }
        // Revisa si es que hay tres en una columna
        for (int j = 0; j < tamano; j++) {
            if (casillas[0][j] == marca && casillas[1][j] == marca && casillas[2][j] == marca) {
                return true;
            }
        }
        // Revisa si hay tres en fila en la diagonal que empieza en 0,0 (hasta arriba a la izquierda)
        if (casillas[0][0] == marca && casillas[1][1] == marca && casillas[2][2] == marca) {
            return true;
        }
        // Revisa si hay tres en linea en la diagonal que empieza en 0,2 (hasta arriba a la derecha)
        if (casillas[0][2] == marca && casillas[1][1] == marca && casillas[2][0] == marca) {
            return true;
        }
        return false; //Si no se cumple ninguno de los casos anteriores retorna falso
    }
    
    
    public boolean estaLleno() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (casillas[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
}
