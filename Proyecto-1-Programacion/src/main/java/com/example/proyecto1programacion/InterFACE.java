package com.example.proyecto1programacion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;


public class InterFACE {

Logic logic = new Logic();


//--------------------------------------------------------------------------------------------------------------------

//Los botones se intancia globalmente para poder usar su evento en el main
Button btt_Reiniciar_left = new Button();
Button btt_Reiniciar_ring = new Button();
Button btt_Menu_Jugar = new Button("Play");
//-------------------------------------------------------------------------------------------------------------------







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

        //                                          Titulo del videoJuego

        Label lb_Menu_Titulo = new Label("TAKEN GAME");//Se crea la etiqueta
        lb_Menu_Titulo.setStyle("-fx-text-fill: #5790B9;");//Se le da un estilo
        lb_Menu_Titulo.setFont(new Font("Kristen ITC",50));//Se le asigna la fuente al titulo

        //--------------------------------------------------------------------------------------------------------------

       //                                      Boton de Jugar

        btt_Menu_Jugar.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #00DC3C ;" +
                                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); //Se le aplica color ;0

        btt_Menu_Jugar.setFont(new Font("Comic Sans MS",36));//Se le asigna la fuente al boton

        //--------------------------------------------------------------------------------------------------------------

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
        });//Evento del boton


        //--------------------------------------------------------------------------------------------------------------

        vbx_pane.setAlignment(Pos.CENTER);//Centra todo en el menu

        vbx_pane.getChildren().addAll(lb_Menu_Titulo,btt_Menu_Jugar,btt_Menu_Cerrar);//Crea los hijos del vbx_pane

        return new Scene(vbx_pane,500,500);
        }

//----------------------------------------------------------------------------------------------------------------------
    public Scene getSceneGame(){//Scene del juego

    //--------------------------------------------------------------------------------------------------------------

    //                                          Intancia de la genreacion de matriz
      GeneradorDeMatriz generador = new GeneradorDeMatriz();//Se inistancia la clase de generacion de matriz
      GeneradorDeMatriz.Puzzle_15 puzzle = generador.new Puzzle_15();//Se hace uso de su constructor

      int MatrixGame [][] = puzzle.Board;//Se guarda la matris generada en una matrix para usarse con mayor facilidad

    //----------------------------------------------------------------------------------------------------------------

    //                                           Instancia del contenedor padre
      BorderPane borderPane = new BorderPane();//El contenedor padre contiene a todos los hijos de la scena

    //-----------------------------------------------------------------------------------------------------------------

    //                                        Interfaz del juego(Los cuadrados)

      GridPane gridPane = new GridPane();//Cuadriculas del juego
      gridPane.setVgap(0.5);
      gridPane.setHgap(0.5);
      borderPane.setCenter(gridPane);//Se le asigna una posicion

      //-----------------------------------------------------------------------------

      //                                        Fila 1 de Botones

      //-------------------------------------------------

      //                      Boton 1

        Button button_1 = new Button("");
        Image img_1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/1.png")));
        ImageView imgV_1 = new ImageView(img_1);
        button_1.setMinSize(100,100);
        button_1.setGraphic(imgV_1);
        gridPane.add(button_1,logic.Getcolum(MatrixGame,1),logic.GetRow(MatrixGame,1));

        button_1.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

       //-----------------------------------------------

      //                     Boton 2
        Button button_2 = new Button("");
        Image img_2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/2.png")));
        ImageView imgV_2 = new ImageView(img_2);

        button_2.setGraphic(imgV_2);

        button_2.setMinSize(100,100);
        gridPane.add(button_2,logic.Getcolum(MatrixGame,2),logic.GetRow(MatrixGame,2));


        button_2.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //  --------------------------------------------------

      //                     Boton 3

        Button button_3 = new Button("");
        Image img_3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/3.png")));
        ImageView imgV_3 = new ImageView(img_3);

        button_3.setGraphic(imgV_3);

        button_3.setMinSize(100,100);
        gridPane.add(button_3,logic.Getcolum(MatrixGame,3),logic.GetRow(MatrixGame,3));


        button_3.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");
      //----------------------------------------------------

      //                     Boton 4

        Button button_4 = new Button("");

        Image img_4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/4.png")));
        ImageView imgV_4 = new ImageView(img_4);

        button_4.setGraphic(imgV_4);

        button_4.setMinSize(100,100);
        gridPane.add(button_4,logic.Getcolum(MatrixGame,4),logic.GetRow(MatrixGame,4));


        button_4.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");
      //--------------------------------------------------------------------------------

      //                                   Fila 2 de Botones

     //-----------------------------------------------------

      //                   Boton 5

        Button button_5 = new Button("");

        Image img_5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/5.png")));
        ImageView imgV_5 = new ImageView(img_5);

        button_5.setGraphic(imgV_5);

        button_5.setMinSize(100,100);
        gridPane.add(button_5,logic.Getcolum(MatrixGame,5),logic.GetRow(MatrixGame,5));


        button_5.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");
      //-----------------------------------------------------

      //                  Boton 6

        Button button_6 = new Button("");

        Image img_6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/6.png")));
        ImageView imgV_6 = new ImageView(img_6);

        button_6.setGraphic(imgV_6);

        button_6.setMinSize(100,100);
        gridPane.add(button_6,logic.Getcolum(MatrixGame,6),logic.GetRow(MatrixGame,6));


        button_6.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //-------------------------------------------------------

      //                 Boton 7
        Button button_7 = new Button("");

        Image img_7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/7.png")));
        ImageView imgV_7 = new ImageView(img_7);

        button_7.setGraphic(imgV_7);

        button_7.setMinSize(100,100);
        gridPane.add(button_7,logic.Getcolum(MatrixGame,7),logic.GetRow(MatrixGame,7));


        button_7.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");
      //--------------------------------------------------------

      //                 Boton 8

        Button button_8 = new Button("");

        Image img_8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/8.png")));
        ImageView imgV_8 = new ImageView(img_8);

        button_8.setGraphic(imgV_8);

        button_8.setMinSize(100,100);
        gridPane.add(button_8,logic.Getcolum(MatrixGame,8),logic.GetRow(MatrixGame,8));

        button_8.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");
      //----------------------------------------------------------------------------------


      //                             Fila 3 de Botones


      //---------------------------------------------------------

      //                  Boton 9


        Button button_9 = new Button("");

        Image img_9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/9.png")));
        ImageView imgV_9 = new ImageView(img_9);

        button_9.setGraphic(imgV_9);

        button_9.setMinSize(100,100);
        gridPane.add(button_9,logic.Getcolum(MatrixGame,9),logic.GetRow(MatrixGame,9));

        button_9.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //----------------------------------------------------------

      //                 Boton 10
        Button button_10 = new Button("");

        Image img_10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/10.png")));
        ImageView imgV_10 = new ImageView(img_10);

        button_10.setGraphic(imgV_10);

        button_10.setMinSize(100,100);
        gridPane.add(button_10,logic.Getcolum(MatrixGame,10),logic.GetRow(MatrixGame,10));

        button_10.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //-----------------------------------------------------------

      //                 Boton 11

        Button button_11 = new Button("");

        Image img_11 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/11.png")));
        ImageView imgV_11= new ImageView(img_11);

        button_11.setGraphic(imgV_11);

        button_11.setMinSize(100,100);
        gridPane.add(button_11,logic.Getcolum(MatrixGame,11),logic.GetRow(MatrixGame,11));

        button_11.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //------------------------------------------------------------

      //                 Boton 12

        Button button_12 = new Button("");

        Image img_12 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/12.png")));
        ImageView imgV_12 = new ImageView(img_12);

        button_12.setGraphic(imgV_12);

        button_12.setMinSize(100,100);
        gridPane.add(button_12,logic.Getcolum(MatrixGame,12),logic.GetRow(MatrixGame,12));

        button_12.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //--------------------------------------------------------------------------------------

      //                                  Fila 4 de Botones

      //-------------------------------------------------------------

      //                  Boton 13

        Button button_13 = new Button("");

        Image img_13 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/13.png")));
        ImageView imgV_13 = new ImageView(img_13);

        button_13.setGraphic(imgV_13);

        button_13.setMinSize(100,100);
        gridPane.add(button_13,logic.Getcolum(MatrixGame,13),logic.GetRow(MatrixGame,13));

        button_13.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //-----------------------------------------------------------


      //                Boton 14

        Button button_14 = new Button("");

        Image img_14 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/14.png")));
        ImageView imgV_14 = new ImageView(img_14);

        button_14.setGraphic(imgV_14);

        button_14.setMinSize(100,100);
        gridPane.add(button_14,logic.Getcolum(MatrixGame,14),logic.GetRow(MatrixGame,14));

        button_14.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //-----------------------------------------------------------

      //                Boton 15

        Button button_15 = new Button("");

        Image img_15 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/15.png")));
        ImageView imgV_15 = new ImageView(img_15);

        button_15.setGraphic(imgV_15);

        button_15.setMinSize(100,100);
        gridPane.add(button_15,logic.Getcolum(MatrixGame,15),logic.GetRow(MatrixGame,15));

        button_15.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //-----------------------------------------------------------

      //                Boton Vacio

        Button button_0 = new Button("");

        Image img_0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/Vacio.png")));
        ImageView imgV_0 = new ImageView(img_0);

        button_0.setGraphic(imgV_0);

        button_0.setMinSize(100,100);
        gridPane.add(button_0,logic.Getcolum(MatrixGame,0),logic.GetRow(MatrixGame,0));

        button_0.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

      //------------------------------------------------------------------









    //-----------------------------------------------------------------------------------------------------------------

    //                                        Boton de cerrar

      VBox vBox_Bottom = new VBox();

      Button btt_close = new Button("Close");

      btt_close.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

      btt_close.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #FF310F;" +
              "-fx-border-radius: 10;" + "-fx-background-radius: 10;");



      btt_close.setMinSize(100,40);
      vBox_Bottom.getChildren().add(btt_close);
      vBox_Bottom.setAlignment(Pos.TOP_CENTER);
      borderPane.setBottom(vBox_Bottom);

      BorderPane.setMargin(vBox_Bottom,new Insets(0,0,50,0));

      btt_close.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

          Platform.exit();

        }
      });

      //----------------------------------------------------------------------------------------------------------------

      //                                     Boton de Reiniciar a la izquierda

      HBox hBox_left = new HBox();
      btt_Reiniciar_left.setMinSize(100,100);
      hBox_left.getChildren().add(btt_Reiniciar_left);
      hBox_left.setAlignment(Pos.CENTER);

      btt_Reiniciar_left.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color:#FFFF;" +
              "-fx-border-radius: 50;" + "-fx-background-radius: 50;");

      Image img_Restart = new Image("Restart().png");
      ImageView imgV_Restart = new ImageView(img_Restart);

      btt_Reiniciar_left.setGraphic(imgV_Restart);

      borderPane.setLeft(hBox_left);
      BorderPane.setMargin(hBox_left,new Insets(0,25,0,25));



      //----------------------------------------------------------------------------------------------------------------

      //                                          Boton de Reiniciar a la Derecha

        HBox hBox_right = new HBox();


      btt_Reiniciar_ring.setMinSize(100,100);
        hBox_right.getChildren().add(btt_Reiniciar_ring);
        hBox_right.setAlignment(Pos.CENTER);

      btt_Reiniciar_ring.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color:#FFFF;" +
              "-fx-border-radius: 50;" + "-fx-background-radius: 50;");

      Image img_Restart_ring = new Image("Restart.png");
      ImageView imgV_Restart_ring = new ImageView(img_Restart_ring);

      btt_Reiniciar_ring.setGraphic(imgV_Restart_ring);

        borderPane.setRight(hBox_right);
        BorderPane.setMargin(hBox_right,new Insets(0,30,0,25));


    //-----------------------------------------------------------------------------------------------------------------
      HBox hBox_Top = new HBox();
      Label lb_Titulo = new Label("TAKES GAME");
      lb_Titulo.setStyle("-fx-text-fill: #5790B9;");//Se da le asigna el color
      lb_Titulo.setFont(new Font("Kristen ITC",32));//Se le asigna la fuente al titulo

      hBox_Top.getChildren().add(lb_Titulo);
      hBox_Top.setAlignment(Pos.CENTER);
      borderPane.setTop(hBox_Top);
      BorderPane.setMargin(hBox_Top,new Insets(30,0,70,0));


        return new Scene(borderPane,700,700);

    }


}
