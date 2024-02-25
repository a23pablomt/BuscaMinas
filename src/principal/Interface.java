package principal;

import buscaminas.BuscaMinas;
import java.util.Scanner;

public class Interface {
    protected BuscaMinas miObjetoBuscaminas = new BuscaMinas();
    protected Scanner sc = new Scanner(System.in);

    public Interface(){
        System.out.println("Iniciando partida...");
        miObjetoBuscaminas.iniciarPartida();
        jugar();
    }

    public static void main(String[] args) {new Interface();}

    private void jugar(){
        int x;
        int y;
        while (true){
            printTablero();
            System.out.println("Escibe tu fila: ");
            x = Integer.parseInt(sc.next().trim()) - 1;
            System.out.println("Escibe tu columna: ");
            y = Integer.parseInt(sc.next().trim()) - 1;
            miObjetoBuscaminas.revelarPosicion(x, y);
            if(miObjetoBuscaminas.getEstadoJuego() != 'C'){
                if(miObjetoBuscaminas.getEstadoJuego() == 'V') {printTablero(); System.out.println("Victoria!!"); return;}
                if(miObjetoBuscaminas.getEstadoJuego() == 'D') {printTablero(); System.out.println("Derrota :("); return;}
            }
        }
    }

    private void printTablero(){
        for (int i = 0; i <= 9; i++){
            System.out.print("|");
            for (int j = 0; j <= 9; j++){
                System.out.print(miObjetoBuscaminas.getTableroVisual()[i][j]);
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}