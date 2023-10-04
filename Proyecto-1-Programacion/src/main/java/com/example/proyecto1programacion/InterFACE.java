package com.example.proyecto1programacion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InterFACE {


    Button btt_Menu_Jugar = new Button("Play");//Botton instanciado globalmete para poder llavar a su evento en el main


    public Scene getSeceneMenu(){

        VBox vbx_pane = new VBox();
        vbx_pane.setSpacing(50);



        //--------------------------------------------------------------------------------------------------------------
        Label lb_Menu_Titulo = new Label("TAKEN GAME");
        lb_Menu_Titulo.setFont(new Font("Kristen ITC",50));//Se le asigna la fuente al titulo
        //--------------------------------------------------------------------------------------------------------------

        btt_Menu_Jugar.setFont(new Font("OCR A Extended",36));//Se le asigna la fuente al botton
        //--------------------------------------------------------------------------------------------------------------
        Button btt_Menu_Cerrar = new Button("Close");
        btt_Menu_Cerrar.setFont(new Font("OCR A Extended",26));//Se le asigna la fuente al botton
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
