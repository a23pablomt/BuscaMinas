package buscaminas;

public class BuscaMinas {
    private int[][] tablero;
    private char[][] tableroVisual;
    private int casillasLibres;
    private char estadoJuego;

    /**
     * Crea nuestros dos tableros: Uno con minas que guarda la información de la partida
     * y otro que contiene la información de lo que ve el jugador.
     */
    public void iniciarPartida(){
        tablero = new int[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tablero[i][j] = 0;
            }
        }
        tableroVisual = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tableroVisual[i][j] = 'X';
            }
        }
        casillasLibres = 85;
        colocarMinas();
        estadoJuego = 'C';
    }

    private void colocarMinas(){
        for(int i = 0; i < 15; i++){
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (tablero[x][y] != 9){
                tablero[x][y] = 8;
                for(int j = x-1; j < x+2; j++){
                    for(int k = y-1; k < y+2; k++){
                        if(inBounds(j, k)) tablero[j][k]++;
                    }
                }
            }
            else i--;
        }
    }

    /**
     * Revela al jugador la información de una casilla seleccionada y calcula si el jugador gana, pierde o continúa jugando.
     * Además, si la casilla no tiene minas adyacentes revela las contiguas hasta encontrar una casilla con minas cercanas.
     * @param x Fila de la casilla deseada.
     * @param y Columna de la casilla deseada.
     */
    public void revelarPosicion(int x, int y){
        int valorCasilla = tablero[x][y];
        if(tablero[x][y] >= 9) {tableroVisual[x][y] = 'B'; estadoJuego = 'D'; return;}
        tableroVisual[x][y] = (char) ((int) '0' + valorCasilla);
        casillasLibres--;
        if(tablero[x][y] == 0){
            for(int j = x-1; j < x+2; j++){
                for(int k = y-1; k < y+2; k++){
                    if(inBounds(j, k)) if(tableroVisual[j][k] == 'X') revelarPosicion(j, k);
                }
            }
        }
        if(casillasLibres <= 0) estadoJuego = 'V';
        else estadoJuego = 'C';
    }

    private boolean inBounds(int j, int k){
        return (j >= 0 && j <= 9 && k >= 0 && k <= 9);
    }

    /**
     * Devuelve el tablero que ve el jugador. (Podría devolverse una copia)
     * @return El tablero visible para el jugador.
     */
    public char[][] getTableroVisual() {
        return tableroVisual;
    }

    /**
     * Devuelve el estado de la partida: Si el jugador ha ganado, perdido, o si el juego continúa
     * @return Un carácter 'V' (Victoria), 'D' (Derrota) o 'C' (Continúa)
     */
    public char getEstadoJuego() {
        return estadoJuego;
    }
}
