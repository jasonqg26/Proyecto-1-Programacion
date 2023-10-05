package com.example.proyecto1programacion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Transform;

public class InterFACE {


    Button btt_Menu_Jugar = new Button("Play");//Botton instanciado globalmete para poder llavar a su evento en el main



    public Scene getSeceneMenu(){

        VBox vbx_pane = new VBox();//Se crea el contenedor
        vbx_pane.setSpacing(50);

        //------------------------------------------------------------------------------------------------------------

        //                                      Se añade la imagen de fondo del menu

        Image img_fondo = new Image("Fondo.png");
        BackgroundImage bImg = new BackgroundImage(img_fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(700, 700, true, true, true, true));
        Background bGround = new Background(bImg);
        vbx_pane.setBackground(bGround);
        //-------------------------------------------------------------------------------------------------------------






        //--------------------------------------------------------------------------------------------------------------

        //                                          Titulo del videoJuego

        Label lb_Menu_Titulo = new Label("TAKEN GAME");//Se crea la etiqueta
        lb_Menu_Titulo.setStyle("-fx-text-fill: #5790B9;");//Se le un estilo
        lb_Menu_Titulo.setFont(new Font("Kristen ITC",50));//Se le asigna la fuente al titulo




        //--------------------------------------------------------------------------------------------------------------

       //                                      Boton de Jugar

        btt_Menu_Jugar.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #00DC3C ;" +
                                "-fx-border-radius: 10;" + "-fx-background-radius: 10;");

        btt_Menu_Jugar.setFont(new Font("Comic Sans MS",36));//Se le asigna la fuente al botton

        //--------------------------------------------------------------------------------------------------------------




        //-------------------------------------------------------------------------------------------------------------

        //                                      Boton de Cerrar

        Button btt_Menu_Cerrar = new Button("Close");//Se crea el botton cerrar

        //Se le da estilo al boton cerrar
        btt_Menu_Cerrar.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #FF310F;" +
                                 "-fx-border-radius: 10;" + "-fx-background-radius: 10;");


        btt_Menu_Cerrar.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton
        btt_Menu_Cerrar.setOnAction(new EventHandler<ActionEvent>() { //Evento de close con el botton close
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();//Sale
            }
        });


        //--------------------------------------------------------------------------------------------------------------

        vbx_pane.setAlignment(Pos.CENTER);//Centra todo

        vbx_pane.getChildren().addAll(lb_Menu_Titulo,btt_Menu_Jugar,btt_Menu_Cerrar);

        Scene scene = new Scene(vbx_pane,500,500);
        return scene;
        }


    public Scene getSceneGame(){//En proceso no hacer caso
                                //Proxima Scena del Juego


        TilePane tlp_pane = new TilePane();

        Label lb_k = new Label("Hola mi nombre es jason");

        tlp_pane.getChildren().add(lb_k);

        Scene scene = new Scene(tlp_pane,700,700);
        return scene;
    }
}
