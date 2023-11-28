package com.example.proyecto1programacion;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class HelloApplication extends Application {

    InterFACE interFace = new InterFACE();

    Stage stage;

    //Se intancia la clase interface
    @Override
    public void start(Stage stage) throws IOException {

        // Asigna el objeto 'stage' actual al miembro 'this.stage' de la clase.
        this.stage = stage;

        // Establece el objeto 'stage' como el Stage principal de la instancia de InterFACE.
        interFace.setMainStage(stage);


        //Se le da un icono al programa
        stage.getIcons().add(new Image("Logo.png"));

        //Nombre de la pestaña
        stage.setTitle("TAKEN GAME");


            stage.setScene(interFace.getSceneWelcome());//Se crea la Scene del Menu
            stage.initStyle(StageStyle.UNDECORATED);//Se elimina todas las opciones menos la equis roja
            stage.show();
            stage.centerOnScreen();





    }

    public static void main(String[] args) {

launch();
}}