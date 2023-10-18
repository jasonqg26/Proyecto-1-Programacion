package com.example.proyecto1programacion;

//import javax.swing.*;

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
    }}







