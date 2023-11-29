package com.example.proyecto1programacion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LogicFiles  {
    // Archivo de acceso predeterminado
    File fileacceso = new File("acceso.txt");


    public boolean PasswordValidation(String password) {
        // Variables para comprobar la complejidad de la contraseña
        boolean mayuscula = false;
        boolean minuscula = false;
        boolean numero = false;
        boolean simbolo = false;
        char l;

        // Verifica la longitud mínima de la contraseña
        if (password.length() >= 8) {
            // Recorre cada carácter de la contraseña
            for (int i = 0; i < password.length(); i++) {
                l = password.charAt(i);

                // Comprueba si la contraseña contiene un dígito
                if (Character.isDigit(l))
                    numero = true;

                // Comprueba si la contraseña contiene una letra mayúscula
                if (Character.isUpperCase(l))
                    mayuscula = true;

                // Comprueba si la contraseña contiene una letra minúscula
                if (Character.isLowerCase(l))
                    minuscula = true;

                // Comprueba si la contraseña contiene un símbolo
                if (!Character.isLetterOrDigit(l))
                    simbolo = true;
            }

            // Retorna true si la contraseña cumple todos los criterios
            return numero && mayuscula && minuscula && simbolo;
        } else {
            // La contraseña no cumple con la longitud mínima requerida
            return false;
        }
    }


    public PrintStream getPrintStream(String name) {
        java.io.PrintStream ps = null;

        try {
            File PasswordSave = new File(name);
            FileOutputStream fos = new FileOutputStream(PasswordSave, true);
            ps = new PrintStream(fos);
        } catch (FileNotFoundException fnef) {
            // Manejo de excepciones si no se puede crear el archivo
            System.out.println("Problemas con el usuario");
        }

        return ps;
    }


    public void writeTextFile(String userName, String password) {
        try (PrintStream ps = getPrintStream("acceso.txt")) {
            ps.println(userName + ";" + password);
        }
    }


    public BufferedReader getBufferedReader() {
        BufferedReader br = null;

        try {
            FileInputStream fis = new FileInputStream(fileacceso);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
        } catch (FileNotFoundException fne) {
            // Manejo de excepciones si no se puede abrir el archivo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }

        return br;
    }

    public boolean user_found(String userName) {
        boolean user_found_1 = false;

        try (BufferedReader reader = getBufferedReader()) {
            String line = reader.readLine();
            while (line != null) {
                // Busca la línea con el usuario proporcionado y establece user_found_1 en true si lo encuentra
                if (line.startsWith(userName + ";"))
                    user_found_1 = true;
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Manejo de excepciones si no se puede leer el archivo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }

        return user_found_1;
    }

    public boolean password_found(String Password) {
        boolean password_found_1 = false;

        try (BufferedReader reader = getBufferedReader()) {
            String line = reader.readLine();
            while (line != null) {
                // Busca la línea con el usuario proporcionado y establece user_found_1 en true si lo encuentra
                if(line.substring(line.indexOf(";")+1).equals(Password))
                    password_found_1 = true;
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Manejo de excepciones si no se puede leer el archivo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }

        return password_found_1;
    }


    public void changePassword(String userName, String newPassword) {
        File tempFile = new File("temp_acceso.txt");

        try (BufferedReader reader = getBufferedReader();
             PrintStream writer = getPrintStream("temp_acceso.txt")) {

            String line = reader.readLine();

            while (line != null) {
                // Busca la línea con el usuario proporcionado y reemplaza la contraseña
                if (line.startsWith(userName + ";")) {
                    writer.println(userName + ";" + newPassword);
                } else {
                    writer.println(line);
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            // Manejo de excepciones si no se puede leer o escribir en el archivo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer o escribir en el archivo");
            alert.showAndWait();
        }

        // Elimina el archivo original y renombra el archivo temporal
        if (!fileacceso.delete()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setContentText("No se pudo borrar el archivo");
            alert.showAndWait();
        }
        if (!tempFile.renameTo(fileacceso)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setContentText("No se pudo borrar el archivo temporal");
            alert.showAndWait();
        }
    }


    public BufferedReader getBufferedReaderReports() {

        File fileGameList = new File("juegosJugados.txt");
        BufferedReader br = null;

        try {
            FileInputStream fis = new FileInputStream(fileGameList);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

        } catch (FileNotFoundException fne) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }
        return br;
    }

    //Método que determina la cantidad de registros que contiene el archivo
    public int countData() {
        //Lectura del archivo
        BufferedReader br = getBufferedReaderReports();

        int count = 0;
        String register = "";

        try {
            while (register != null) {
                register = br.readLine();

                if (register != null)
                    count++;
            }//End while
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }

        return count;
    }


    public Player[] getDataFiletoArray() {

        Player arrayPlayerData[] = new Player[countData()];
        int indexArray = 0;

        BufferedReader br = getBufferedReaderReports();

        try {
            String register = br.readLine();

            while (register != null) {
                StringTokenizer sT = new StringTokenizer(register, ";");

                String Player = sT.nextToken();
                String date = sT.nextToken();
                String durationTime = sT.nextToken();
                String totalMovements = sT.nextToken();


                Player jugador = new Player(Player,date,durationTime,totalMovements);
                arrayPlayerData[indexArray] = jugador;
                indexArray++;

                register = br.readLine();

            }//End while
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText("Problemas con el archivo");
            alert.setContentText("No se pudo leer del archivo");
            alert.showAndWait();
        }

        return arrayPlayerData;
    }
    public ObservableList<Player> getDataTableView() {

        ArrayList arrayPlayers = new ArrayList();

        Player tempPlayer[] = getDataFiletoArray();

        for (int i = 0; i < tempPlayer.length; i++)
            arrayPlayers.add(tempPlayer[i]);

        ObservableList<Player> obListDataPlayers= FXCollections.observableArrayList(arrayPlayers);

        return obListDataPlayers;
    }






}