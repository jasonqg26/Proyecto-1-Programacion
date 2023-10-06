package com.example.proyecto1programacion;

public class Logic {

    public int Getcolum(int Matrix [][],int num){

    int y = 0;
    for (int row = 0; row < 4; row++){
        for (int colom = 0; colom < 4; colom++){
            if(Matrix[row][colom] == num)
                y = colom;}
    }

    return y;
    }

    public int GetRow(int Matrix [][],int num){

        int x = 0;
        for (int row = 0; row < 4; row++){
            for (int colom = 0; colom < 4; colom++){
                if(Matrix[row][colom] == num)
                    x = row;}
        }

        return x;
    }

    public String ShowSuma(int suma [][]){

        String list = "";

        for(int i = 0; i < suma.length;i++) {


            for (int j = 0; j < suma[0].length; j++)
                list += suma[i][j] + "  ";


            list += "\n";
        }

        return list;

    }

}




