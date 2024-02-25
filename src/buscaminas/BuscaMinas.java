package buscaminas;

public class BuscaMinas {
    private int[][] tablero;
    private char[][] tableroVisual;
    private int casillasLibres;
    private char estadoJuego;

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

    public char[][] getTableroVisual() {
        return tableroVisual;
    }

    public char getEstadoJuego() {
        return estadoJuego;
    }
}
