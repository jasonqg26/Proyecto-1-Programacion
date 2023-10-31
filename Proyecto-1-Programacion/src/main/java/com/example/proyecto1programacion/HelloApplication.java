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
        this.stage = stage; // Asigna el objeto 'stage' actual al miembro 'this.stage' de la clase.

        // Establece el objeto 'stage' como el Stage principal de la instancia de InterFACE.
        interFace.setMainStage(stage);

        stage.getIcons().add(new Image("Logo.png"));

        //Nombre de la pestaña
        stage.setTitle("TAKEN Menu");


            stage.setScene(interFace.getSceneWelcome());//Se crea la Scene del Menu
            stage.initStyle(StageStyle.UNDECORATED);//Se elimina todas las opciones menos la equis roja
            stage.show();
            stage.centerOnScreen();


        // Configura un manejador de eventos para el botón "Play" del menú
            interFace.btt_Menu_Jugar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) { //Evento del botton play del menu
                    stage.setTitle("TAKEN Game");//Se cambia el titulo
                    stage.setScene(interFace.getSceneGame(stage));//Se crea la scena del menu
                    stage.centerOnScreen();

                }
            });

        // Configura un manejador de eventos para el botón "Instructions" del menú
        interFace.btt_Menu_Instrucciones.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { //Evento del botton instructions del menu
                interFace.mostrarInstrucciones();
            }
        });

    }

    public static void main(String[] args) {

launch();

}}