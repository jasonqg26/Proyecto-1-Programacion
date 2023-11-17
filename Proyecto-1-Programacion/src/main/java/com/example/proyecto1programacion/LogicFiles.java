package com.example.proyecto1programacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LogicFiles {


    public boolean PasswordValidation(String password) {

        boolean mayuscula = false;
        boolean minuscula = false;
        boolean numero = false;
        boolean simbolo = false;
        char l;


        if (password.length() >= 8) {


            for (int i = 0; i < password.length(); i++) {
                l = password.charAt(i);

                if (Character.isDigit(l))
                    numero = true;

                if (Character.isUpperCase(l))
                    mayuscula = true;

                if (Character.isLowerCase(l))
                    minuscula = true;

                if (!Character.isLetterOrDigit(l))
                    simbolo = true;

            }

            if (numero == true && mayuscula == true && minuscula == true && simbolo == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;


        }


    }


    public PrintStream getPrintStream() {
        java.io.PrintStream ps = null;


        try {
            File PasswordSave = new File("acceso.txt");
            FileOutputStream fos = new FileOutputStream(PasswordSave, true);
            ps = new PrintStream(fos);
        } catch (FileNotFoundException fnef) {

            System.out.println("Problemas con el usuario");

        }

        return ps;
    }

    public void writeTextFile(String text) {

        PrintStream ps = getPrintStream();

        ps.println(text);


    }
}