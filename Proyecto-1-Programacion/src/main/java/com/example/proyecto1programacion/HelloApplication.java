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




        stage.getIcons().add(new Image("Logo.png"));

        //Nombre de la pestaña
        stage.setTitle("TAKEN Menu");


            stage.setScene(interFace.getSeceneMenu());//Se crea la Scene del Menu
            stage.initStyle(StageStyle.UNDECORATED);//Se elimina todas las opciones menos la equis roja
            stage.show();
            stage.centerOnScreen();



            interFace.btt_Menu_Jugar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) { //Evento del botton play del menu
                    stage.setTitle("TAKEN Game");//Se cambia el titulo
                    stage.setScene(interFace.getSceneGame());//Se crea la scena del menu
                    stage.centerOnScreen();

                }
            });

            interFace.btt_Reiniciar_left.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage.setScene(interFace.getSceneGame());
                    stage.centerOnScreen();

                }
            });

            interFace.btt_Reiniciar_ring.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage.setScene(interFace.getSceneGame());
                    stage.centerOnScreen();

                }
            });

    }


    public static void main(String[] args) {


launch();

}}