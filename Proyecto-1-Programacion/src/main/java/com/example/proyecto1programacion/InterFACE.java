package com.example.proyecto1programacion;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;


public class InterFACE {

    private Stage stage;

    public void setMainStage(Stage stage) {
        this.stage = stage;
    }

Logic logic = new Logic();
GeneradorDeMatriz generador = new GeneradorDeMatriz();//Se inistancia la clase de generacion de matriz
int MatrixGame [][] = generador.Board;//Se guarda la matris generada en una matrix para usarse con mayor facilidad



//--------------------------------------------------------------------------------------------------------------------

//Los botones se intancia globalmente para poder usar su evento en el main
Button btt_Menu_Jugar = new Button("Play");
Button btt_Menu_Instrucciones = new Button("Instructions");


//-------------------------------------------------------------------------------------------------------------------
public Scene getSceneWelcome (){
                                         //Escena de Bienvenida
    // -------------------------------------------------------------------------------------------------------------
    VBox vBox_Welcome = new VBox(); // se instancia el contendor//
    vBox_Welcome.setSpacing(10); // espacio entre los objetos
    //--------------------------------------------------------------------------------------------------------------


    // se le coloca una imagen como fondo a la escena
    Image img_fondo = new Image("Fondo.png");

    BackgroundImage bImg = new BackgroundImage(img_fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(700, 700, true, true, true, true));
    Background bGround = new Background(bImg);
    vBox_Welcome.setBackground(bGround);


    //--------------------------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------------------------

    // se crea una label que indica la bienvenida al jugador
    Label labelWelcome = new Label("Welcome");
    labelWelcome.setStyle(
            "-fx-text-fill: #26c9e5;" + // Color del texto
                    "-fx-font-family: 'Kristen ITC';" + // Fuente
                    "-fx-font-size: 100px;" + // Tamaño de fuente
                    "-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 0, 0);" // Efecto de sombra
    );


    //--------------------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------------------

    // se crea una label para solicitar el nombre al usario
    Label lb_nameInput = new Label("Insert your name");

    lb_nameInput.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
    lb_nameInput.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #FF310F;" +
            "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label
    lb_nameInput.setMinSize(100,40); // tamaño del label




    // se crea un Txt que obtendra el name del usuario
    TextField Txt_Name = new TextField();
    // Establece el tamaño y la fuente del TextField
    Txt_Name.setFont(new Font("Kristen ITC", 18));
    Txt_Name.setMaxSize(400,100); // tamaño del Txt



    // se crea un boton para que cuando el usuario ingrese su name, lo envie a los creditos y posteriormente al menu de inicio
    Button btt_Start = new Button("Start");
    btt_Start.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton
    btt_Start.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #FF310F;" +
            "-fx-border-radius: 10;" + "-fx-background-radius: 10;");// se le da estilo
    btt_Start.setMinSize(100, 40);// se le da tamaño



    btt_Start.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // Oculta la escena de bienvenida
            stage.hide();

            // Muestra la escena de créditos
            Stage creditsStage = new Stage();
            creditsStage.initStyle(StageStyle.UNDECORATED);
            Scene creditsScene = getSceneCredits();
            creditsStage.setScene(creditsScene);
            creditsStage.show();

            // manejador de eventos para controlar el cierre de la escena de créditos después de X segundos
            EventHandler<ActionEvent> timelineHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    creditsStage.close();
                    stage.setScene(getSeceneMenu());// Vuelve a la escena principal
                    stage.show(); // Muestra nuevamente la escena principal
                }
            };
            // línea de tiempo para cerrar la escena de créditos
            Timeline durationSceneCredits = new Timeline(new KeyFrame(Duration.seconds(8), timelineHandler));
            durationSceneCredits.play();
        }
    });



    // Establece la alineación del VBox
    vBox_Welcome.setAlignment(Pos.CENTER);

    // se agrega cada objeto al Vbox
    vBox_Welcome.getChildren().addAll(labelWelcome,lb_nameInput,Txt_Name,btt_Start);
    stage.centerOnScreen();

    Scene scene = new Scene(vBox_Welcome, 500, 500);

    return scene;
}
//--------------------------------------------------------------------------------------------------------------
    //                                      Creditos
// Función para obtener la escena de créditos
public Scene getSceneCredits() {
    // escena de créditos
    VBox vBoxCredits = new VBox();
    vBoxCredits.setAlignment(Pos.CENTER);
    vBoxCredits.setSpacing(20);

    // etiquetas de créditos
    Label labelWelcome = creditsLabels("¡Bienvenido!", 30);
    Label labelCredits = creditsLabels("Créditos", 30);
    Label labelCredit1 = creditsLabels("Desarrollado por:\nAlexander Fallas Sanabria C32838\n" +
            "Jason Quesada Gómez C36213\nFabian Quesada Cordero C36202", 20);
    Label labelCredit2 = creditsLabels("Proyecto del curso IF2000", 20);
    Label labelCredit3 = creditsLabels("Año: 2023", 20);

    // Agrega las etiquetas al contenedor VBox
    vBoxCredits.getChildren().addAll(labelWelcome, labelCredits, labelCredit1, labelCredit2, labelCredit3);

    // Crea la escena de créditos
    Scene creditsScene = new Scene(vBoxCredits, 500, 500);
    return creditsScene;
}
    //--------------------------------------------------------------------------------------------------------------

   //                                          Perosonalizador de etiquetas

    // Función para crear etiquetas con fuente y estilo personalizados
    private Label creditsLabels(String text, int fontSize) {
        Label label = new Label(text);
        // Establece la fuente y el estilo de las etiquetas
        label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, fontSize));
        label.setStyle("-fx-text-fill: #000000");
        label.setAlignment(Pos.CENTER);
        return label;
    }


    //--------------------------------------------------------------------------------------------------------------
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

        //                                      Boton de Instrucciones


        // Establece el estilo del botón de menú "Instrucciones"
        btt_Menu_Instrucciones.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" +
                "-fx-text-fill: #1D5DEC;" + "-fx-border-radius: 10;" + "-fx-background-radius: 10;"
        );

        //fuente personalizada al botón
        btt_Menu_Instrucciones.setFont(new Font("Comic Sans MS", 26));

        // evento del botón para mostrar las instrucciones al hacer clic
        btt_Menu_Instrucciones.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mostrarInstrucciones();
            }
        });




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

        vbx_pane.setAlignment(Pos.CENTER);//Centratodoenelmenu

        vbx_pane.getChildren().addAll(lb_Menu_Titulo,btt_Menu_Jugar,btt_Menu_Instrucciones,btt_Menu_Cerrar);//Crea los hijos del vbx_pane

        return new Scene(vbx_pane,500,500);
        }

//----------------------------------------------------------------------------------------------------------------------

    //                                      Escena de instrucciones
    public void mostrarInstrucciones() {
    // nuevo escenario para mostrar las instrucciones
    Stage instructionsStage = new Stage();
    instructionsStage.setTitle("Instrucciones");
    instructionsStage.initStyle(StageStyle.UNDECORATED);

    // etiquetas de instrucciones
    Label[] instructionLabels = {
            createInstructionLabel("1.The game consists of a 4X4 board, containing \n16 numbers (0-15), with" +
                    " the objective of \narranging the numbers in order, starting at 1 \nand ending at 15" +
                    " (empty space at the end)"),
            createInstructionLabel("2.Only the empty square is allowed to be moved, \n exchanging numbers " +
                    "with that square until all\n of them are accommodated."),
            createInstructionLabel("3.It is only allowed to move the number that is \nvertically and" +
                    " horizontally to the empty square\n, any other movement is invalid."),
            createInstructionLabel("4.There is no time or move limit, so have fun with\n the game.")
    };

    //contenedor vertical para las etiquetas de instrucciones
    VBox instructionsPane = new VBox(20, instructionLabels);
    instructionsPane.setAlignment(Pos.CENTER);

    // Configura el fondo de la escena con una imagen
    Image backgroundImage = new Image("Fondo3.png.jpg");
    BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            new BackgroundSize(700, 700, true, true, true, true));
    Background background = new Background(backgroundImg);
    instructionsPane.setBackground(background);

    // nueva escena con el contenedor de instrucciones
    Scene instructionsScene = new Scene(instructionsPane, 500, 500);

    // botón de "Return" para volver al menú
    Button returnButton = new Button("Return");
    returnButton.setStyle("-fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #0000FF; " +
            "-fx-border-radius: 10; -fx-background-radius: 10;");
    returnButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 26));

    // Asigna un evento al botón para volver al menú principal y cerrar las instrucciones
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(getSeceneMenu());
                instructionsStage.close();
            }
        });

    // Agrega el botón al contenedor de instrucciones
    instructionsPane.getChildren().add(returnButton);

    // Configura la escena y muestra el escenario de instrucciones
    instructionsStage.setScene(instructionsScene);
    instructionsStage.show();
}

//----------------------------------------------------------------------------------------------------------------------

    //                                    Personalizador de etiquetas
    //crea etiquetas de instrucción personalizadas
    private Label createInstructionLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        label.setStyle("-fx-text-fill: #000000");
        label.setAlignment(Pos.CENTER);
        return label;
    }

    //-----------------------------------------------------------------------------------------------------------------

    public Scene getSceneGame(Stage stage){

        //Scene del juego



    //                                           Instancia del contenedor padre
      BorderPane borderPane = new BorderPane();//El contenedor padre contiene a todos los hijos de la scene

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
                    if(generador.juegoCompletado())
                        ActualizarEscenarioWin(stage);
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
    public Scene getSceneWin (){

        // -------------------------------------------------------------------------------------------------------------
        BorderPane b_Pane = new BorderPane(); // se instancia el contendor//----------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        // se le coloca una imagen como fondo a la escena
        Image img_fondo = new Image("Fondo.png");

        BackgroundImage bImg = new BackgroundImage(img_fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(700, 700, true, true, true, true));
        Background bGround = new Background(bImg);
        b_Pane.setBackground(bGround);
        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        // se crea una label que indica que el juego se ha ganado
        Label labelWin = new Label("You win");
        labelWin.setStyle(
                "-fx-text-fill: #70fd00;" + // Color del texto
                        "-fx-font-family: 'Kristen ITC';" + // Fuente
                        "-fx-font-size: 100px;" + // Tamaño de fuente
                        "-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 0, 0);" // Efecto de sombra
        );
        b_Pane.setCenter(labelWin);
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        // se crea un Vbox que contendra los botones
        VBox vBox_Bottom = new VBox();
        vBox_Bottom.setSpacing(10); // espacio entre los botones

        // se crea un boton de volver a jugar
        Button btt_playAgain = new Button("Play Again");
        btt_playAgain.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

        btt_playAgain.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #FF310F;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al boton



        btt_playAgain.setMinSize(100,40); // tamaño del boton
        vBox_Bottom.getChildren().add(btt_playAgain);// se agrega al Vbox
        vBox_Bottom.setAlignment(Pos.TOP_CENTER);// se le asgina la posicion
        b_Pane.setBottom(vBox_Bottom);// se establece el boton

        BorderPane.setMargin(vBox_Bottom,new Insets(0,0,50,0));

        btt_playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setScene(getSceneGame(stage));
                stage.centerOnScreen();

            }
        });

        //--------------------------------------------------------------------------------------------------------------


        // se crea un boton de close
        Button btt_close = new Button("Close");

        btt_close.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

        btt_close.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #FF310F;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;");// se le da estilo



        btt_close.setMinSize(100,40);//tamaño
        vBox_Bottom.getChildren().add(btt_close);// se agrega al Vbox
        vBox_Bottom.setAlignment(Pos.TOP_CENTER);// se le asigna la posicion
        b_Pane.setBottom(vBox_Bottom); // se establece el boton

        BorderPane.setMargin(vBox_Bottom,new Insets(0,0,50,0));

        btt_close.setOnAction(new EventHandler<ActionEvent>() {  // se le asigna la funcion al boton
            @Override
            public void handle(ActionEvent actionEvent) {

                Platform.exit(); // cerrar la app

            }
        });

        //--------------------------------------------------------------------------------------------------------------





        Scene scene = new Scene(b_Pane, 500, 500);

        return scene;
    }

    public void ActualizarEscenarioWin (Stage stage){

        stage.setScene(getSceneWin());
        stage.centerOnScreen();
    }


}
