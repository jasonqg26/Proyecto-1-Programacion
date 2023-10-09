package com.example.proyecto1programacion;

//import javax.swing.*;

public class Logic {

    private static final int boardSize = 4;
    private static final int limit = boardSize * boardSize - 1;

    int[][] board;
    int posEmpty;

    public Logic() {
        board = new int[boardSize][boardSize];
        startBoard();
    }

    public void startBoard() {
        posEmpty = (int) (Math.random() * (boardSize * boardSize));

        int posNumbers;

        for (int k = 1; k <= limit; k++) {
            posNumbers = (int) (Math.random() * (boardSize * boardSize));

            while (posNumbers == posEmpty || board[posNumbers / boardSize][posNumbers % boardSize] != 0) {
                posNumbers = (posNumbers + 1) % (boardSize * boardSize);
            }

            board[posNumbers / boardSize][posNumbers % boardSize] = k;
        }
    }

    public void showBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                if (board[row][column] < 10) {
                    System.out.print(" " + board[row][column] + " ");
                } else {
                    System.out.print(board[row][column] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean juegoCompletado() {
        int expectedValue = 0;

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                expectedValue++;
                if (board[row][column] != expectedValue && expectedValue != limit + 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public void haceJugada(int i, int j) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int k = 0; k < 4; k++) {
            int newRow = i + dx[k];
            int newColumn = j + dy[k];
            int emptyRow = posEmpty / boardSize;
            int emptyColumn = posEmpty % boardSize;

            if (newRow == emptyRow && newColumn == emptyColumn) {
                int temp = board[i][j];
                board[i][j] = board[emptyRow][emptyColumn];
                board[emptyRow][emptyColumn] = temp;
                posEmpty = i * boardSize + j;
                return;
            }
        }

        System.out.println("Movimiento no válido. Elige otra celda.");
    }

    public void jugar() {
        //JOptionPane.showMessageDialog(null, "Bienvenido al juego del Puzzle 15. Para mover una celda, ingresa la fila y la columna separadas por un espacio.");

        while (!juegoCompletado()) {
            // Mostrar el tablero
            showBoard();

            // Obtener la jugada del usuario usando JOptionPane
            String input = " ";//JOptionPane.showInputDialog("Ingresa la fila y la columna de la celda que deseas mover (Ejemplo: 1 2):");

            if (input != null) {
                // Dividir la entrada en fila y columna
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    try {
                        int fila = Integer.parseInt(parts[0]);
                        int columna = Integer.parseInt(parts[1]);

                        // Verificar si la jugada es válida antes de realizarla
                        if (movimientoValido(fila, columna)) {
                            // Realizar la jugada
                            haceJugada(fila, columna);
                        } else {
                            //JOptionPane.showMessageDialog(null, "Movimiento no válido. Elige otra celda.");
                        }
                    } catch (NumberFormatException e) {
                        //JOptionPane.showMessageDialog(null, "Entrada no válida. Ingresa la fila y la columna separadas por un espacio.");
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "Entrada no válida. Ingresa la fila y la columna separadas por un espacio.");
                }
            } else {
                // El usuario cerró la ventana de entrada, puedes manejar este caso adecuadamente.
                //JOptionPane.showMessageDialog(null, "Juego cancelado.");
                break;
            }
        }

        // El juego se ha completado
        showBoard();
        //JOptionPane.showMessageDialog(null, "¡Felicidades, has completado el juego!");
    }

    public boolean movimientoValido(int i, int j) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int k = 0; k < 4; k++) {
            int newRow = i + dx[k];
            int newColumn = j + dy[k];
            int emptyRow = posEmpty / boardSize;
            int emptyColumn = posEmpty % boardSize;

            if (newRow == emptyRow && newColumn == emptyColumn) {
                return true;
            }
        }

        return false;
    }
}





//public class Logic {

    //public int Getcolum(int Matrix [][],int num){

    //int y = 0;
   // for (int row = 0; row < 4; row++){
       // for (int colom = 0; colom < 4; colom++){
            //if(Matrix[row][colom] == num)
                //y = colom;}
    //}

    //return y;
    //}

    //public int GetRow(int Matrix [][],int num){

        //int x = 0;
        //for (int row = 0; row < 4; row++){
            //for (int colom = 0; colom < 4; colom++){
                //if(Matrix[row][colom] == num)
                    //x = row;}
        //}

        //return x;
    //}

    //public String ShowSuma(int suma [][]){

        //String list = "";

        //for(int i = 0; i < suma.length;i++) {


            //for (int j = 0; j < suma[0].length; j++)
                //list += suma[i][j] + "  ";


            //list += "\n";
        //}

        //return list;

    //}

//}




