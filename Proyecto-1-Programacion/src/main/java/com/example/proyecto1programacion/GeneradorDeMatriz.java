package com.example.proyecto1programacion;

public class GeneradorDeMatriz {
    int Board [][]; // matriz del tablero de juego
    int posEmpty;
    int VacioF = 0;
    int VacioC = 0;

    public GeneradorDeMatriz(){
        Board = new int[4][4];


        StartBoard();//Crea aleatoriamente el tabalero del juego


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

            while (posNumbers == posEmpty|| Board[posNumbers / 4][posNumbers % 4]!=0)

                posNumbers = (posNumbers + 1 ) % 16 ;

            // Asignar el valor de k en la posicion de la matriz

            Board[posNumbers / 4 ][ posNumbers % 4 ] = k;


        }

    }


    public void printBoard(){

        for (int  row = 0; row < 4; row++) {//muevele las filas

            for (int colum = 0; colum < 4; colum++)
                if (Board[row][colum] < 10)
                    System.out.print(" " + Board[row][colum] + " ");
                else
                    System.out.print(Board[row][colum] + " ");

            System.out.println();

        }}


    public boolean movimientoValido(int i, int j){
        //Variables
        boolean movimiento_valido_row = false;
        boolean movimiento_valido_colum = false;

        for (int FilaVacio = 0; FilaVacio < Board.length;FilaVacio++){
            for (int ColumaVacio =0;ColumaVacio<Board[0].length;ColumaVacio++) {
                //si se encuentra la casilla que contiene el valor vacio
                if (Board[FilaVacio][ColumaVacio] == 0) {
                    //se guardan las coordenadas de la casilla
                    VacioF = FilaVacio;
                    VacioC = ColumaVacio;
                    break;
                }
            }
        }

        //Verifica que el moviento sea legal
        if (VacioF == i-1 || VacioF == i+1 )
            movimiento_valido_row = true;

        if (VacioC == j-1 || VacioC == j+1)
            movimiento_valido_colum = true;


        //Verifica que el movimineto no sea diagonal
        if(movimiento_valido_colum == true && movimiento_valido_row == true)
            return false;
        else if (movimiento_valido_row == false && movimiento_valido_colum == false) {
            return false;
        } else return true;
    }

    public void haceJugada(int i, int j){
        //Realiza el cambio con las la posicion del 0 guardada en movimientoValido
        Board[VacioF][VacioC] = Board[i][j];
        //Pone en la posicion que se le dio como parametro el cero
        Board[i][j] = 0;
    }
    public boolean juegoCompletado(){
        //contador que va del 0 al 16
        int contador = 0;
        //se recore la matriz
        for (int flow = 0; flow < 4 ; flow++)
            for (int colum = 0; colum < 4; colum++){
                contador++;
                //se verifa que el cero esté colocado en la posicion correcta
                if (Board[3][3] == 0){
                    //Si lo está se verifa que el contador no sea 16
                    if (contador == 16)
                        return true;
                    //Va verificando si la posicion 1 es igual al 1 si no es asi retorna un falso
                    if(!(Board[flow][colum] == contador))
                        return false;
                    //si la posicion del cero no es correcta es porque todavia no ah finalizado el juego
                }else return false;
            }
        return false;
    }


}
