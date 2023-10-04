package com.example.proyecto1programacion;

public class GeneradorDeMatriz {

    public class Puzzle_15 {
        //dclaracion de variables globales

        int Board [][]; // matriz del tablero de juego
        int posEmpty;

        public Puzzle_15(){

            Board = new int[4][4];

                StartBoard();

        }
        //Este metodo inicializa el tablero dejando el espacio em blanco y asignando los numeros del 1 al 15
        public void StartBoard (){
            // Determinar el valor aleatorio de la posicion vacia que va a tomar la matriz
            posEmpty = (int)(Math.random()*16);

            int posNumbers;

            //k es el numero a colocar en el tablero es decir numeros del 1 al 15
            for (int k = 1; k < 16; k++){
                //genera la posicion aleatoria donde colocar k
                posNumbers = (int)(Math.random()*16);

                //Buscar un espacio disponible para la variable posNumbers

                while (posNumbers ==posEmpty|| Board[posNumbers / 4][posNumbers%4]!=0)

                    posNumbers = (posNumbers + 1 ) % 16 ;

                // Asignar el valor de k en la posicion de la matriz

                Board[posNumbers / 4 ][ posNumbers % 4 ] = k;


            }

        }
        public void printBoard(){

            for (int  row = 0; row < 4; row++){//muevele las filas

                for(int colum = 0; colum < 4 ; colum++)
                    if (Board[row][colum]<10)
                        System.out.print(" " +Board[row][colum] + " ");
                    else
                        System.out.print(Board[row][colum] + " ");

                System.out.println();


            }

        }

    }
}
