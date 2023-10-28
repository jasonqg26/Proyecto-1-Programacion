package com.example.proyecto1programacion;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;


public class InterFACE {

Logic logic = new Logic();
GeneradorDeMatriz generador = new GeneradorDeMatriz();//Se inistancia la clase de generacion de matriz
int MatrixGame [][] = generador.Board;//Se guarda la matris generada en una matrix para usarse con mayor facilidad



//--------------------------------------------------------------------------------------------------------------------

//Los botones se intancia globalmente para poder usar su evento en el main
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
    public Scene getSceneGame(Stage stage){

        //Scene del juego



    //                                           Instancia del contenedor padre
      BorderPane borderPane = new BorderPane();//El contenedor padre contiene a todos los hijos de la scena

    //-----------------------------------------------------------------------------------------------------------------

    //                                        Interfaz del juego(Los cuadrados)
        //                                      creacion del gridPane

      GridPane gridPane = new GridPane();//Cuadriculas del juego
      gridPane.setVgap(0.5);
      gridPane.setHgap(0.5);
      borderPane.setCenter(gridPane);//Se le asigna una posicion


    //---------------------------------------------------------------------------------------------------------------
    //                                            creacion de los botones
     //Se crea 15 botones
        for (int i = 0; i <= 15; i++) {

            Button button = new Button();
            //Se le asigna una imagen nueva a cada boton segun el indice del for
            Image img_1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numeros/" + i + ".png")));
            ImageView imgV_1 = new ImageView(img_1);
            //Tanaño minimo de los btones
            button.setMinSize(100, 100);
            //Se le asigana la imagagen antes creada
            button.setGraphic(imgV_1);
            //Se agrega al gridPane
            gridPane.add(button, logic.Getcolum(MatrixGame, i), logic.GetRow(MatrixGame, i));
            //Se le da los estylos nesesarios
            button.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-background-color: #FFFFFF;");

            // Agrega propiedades personalizadas para la fila y la columna a cada botón
            button.getProperties().put("fila", logic.GetRow(MatrixGame, i));
            button.getProperties().put("columna", logic.Getcolum(MatrixGame, i));


            int finalI = i;

            //Lo que sucede al precionar algun botton de los ateriormente creados
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // Obtiene la fila y la columna del botón presionado
                    int fila = Integer.valueOf((button.getProperties().get("fila")).toString());
                    int columna = Integer.valueOf((button.getProperties().get("columna")).toString());
                    //Verifica si la jugada es valida si lo es realiza el moviemto
                   if (generador.movimientoValido(fila,columna)){
                       //Realiza el movimiento
                       generador.haceJugada(fila,columna);
                       //Actualiza el stage
                        ActualizarEcenario(stage);
                   }else {
                    // Cambiar el estilo del botón cuando el movimiento no es válido
                       if (finalI != 0){
                           //Crea una imagen de que el boton presionado no se puede mover
                       Image img_2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numero Invalido/" + finalI + ".png")));
                       ImageView imgV_2 = new ImageView(img_2);
                       button.setGraphic(imgV_2);}
                       //Contrala el tiempo en que la imagen de error se mantiene
                       PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                       pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent actionEvent) {
                               //Despues del tiempo se vuelve a la imagen anterior
                               button.setGraphic(imgV_1);
                           }
                       });//end de que ahcer despues de los dos segundos
                       //Inicia el tiempo
                       pauseTransition.play();
                    }//End del else
                }
            });//End del evento
        }//End del for





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
      Button btt_Reiniciar_left = new Button();

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



        //Evento del botton de reinicio de la izquierda
      btt_Reiniciar_left.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
              int [][] temp = new int[4][4];

              generador.Board = temp;
              generador.StartBoard();
              MatrixGame = generador.Board;
              ActualizarEcenario(stage);



          }
      });



      //----------------------------------------------------------------------------------------------------------------

      //                                          Boton de Reiniciar a la Derecha

        HBox hBox_right = new HBox();
        Button btt_Reiniciar_ring = new Button();

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


        //Evento del botton de reinicio de la Derecha
        btt_Reiniciar_ring.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int [][] temp = new int[4][4];

                generador.Board = temp;
                generador.StartBoard();
                MatrixGame = generador.Board;


                ActualizarEcenario(stage);

            }
        });


    //-----------------------------------------------------------------------------------------------------------------
      //                                          titulo del juego


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

public void ActualizarEcenario(Stage stage){
        stage.setScene(getSceneGame(stage));
}
}
