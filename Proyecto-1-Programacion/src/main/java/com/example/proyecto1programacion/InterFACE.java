package com.example.proyecto1programacion;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class InterFACE {

    private Stage stage;
    public void setMainStage(Stage stage) {
        this.stage = stage;
    }

Logic logic = new Logic();
LogicFiles logicFiles = new LogicFiles();
GeneradorDeMatriz generador = new GeneradorDeMatriz();//Se inistancia la clase de generacion de matriz

int[][] MatrixGame = generador.Board;//Se guarda la matris generada en una matrix para usarse con mayor facilidad


Boolean logged_in = false;


/*
* Son variables de instancia para mantener un seguimiento
* continuo de la cantidad de movimientos y el tiempo
* a lo largo del juego.
*/
// Etiqueta que muestra el número de movimientos realizados en el juego.
Label labelMovimientos = new Label("Movements:\n0");

// Variable de instancia que almacena la cantidad de movimientos.
int contadorMovimientos;

// Etiqueta que muestra el tiempo transcurrido durante el juego.
Label timeLabel = new Label("Time: 0");

// Cronómetro utilizado para medir el tiempo de juego.
Timeline timeline;

// Indica si el cronómetro está en ejecución.
boolean cronometroEnEjecucion = false;

// Indica si el cronómetro está en pausa.
boolean cronometroEnPausa = false;



String UserName = "";

//-------------------------------------------------------------------------------------------------------------------
public Scene getSceneWelcome (){

                                         //Escena de Bienvenida
    // -------------------------------------------------------------------------------------------------------------
    VBox vBox_Welcome = new VBox(); // se instancia el contendor//
    vBox_Welcome.setSpacing(20); // espacio entre los objetos



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
                    "-fx-text-fill: #5790B9;" + // Color del texto
                    "-fx-font-family: 'Kristen ITC';" + // Fuente
                    "-fx-font-size: 100px;"  // Tamaño de fuente
    );


    //--------------------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------------------

    // se crea una label para solicitar el nombre al usuario
    Label lb_nameInput = new Label("Insert your nameUser");

    lb_nameInput.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
    lb_nameInput.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
            "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label




    TextField Txt_Name;
    // se crea un Txt que obtendra el name del usuario
    Txt_Name = new TextField();
    // Establece el tamaño y la fuente del TextField
    Txt_Name.setFont(new Font("Comic Sans MS", 15));
    Txt_Name.setMaxSize(265,100); // tamaño del Txt



    // se crea una label para solicitar el password al usuario
    Label lb_passwordInput = new Label("Password");

    lb_passwordInput.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
    lb_passwordInput.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
            "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label


    // se crea un Txt que obtendra el password del usuario
    PasswordField pss_Password = new PasswordField();
    // Establece el tamaño y la fuente del TextField
    pss_Password.setFont(new Font("Comic Sans MS", 15));
    pss_Password.setMaxSize(265,100); // tamaño del Txt



    // se crea un boton para que cuando el usuario ingrese su name, lo envie a los creditos y posteriormente al menu de inicio
    Button btt_Start = new Button("Start");
    btt_Start.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton
    btt_Start.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
            "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF");// se le da estilo
    btt_Start.setMinSize(150, 60);// se le da tamaño


    //se crea una label para informar al usuario la situacion con la contraseña
    Label lb_infoPassword = new Label("");
    Label lb_infoUserName = new Label("");


    btt_Start.setOnAction(event -> {




        //Se le asigna lo que haya en el txt de username a la variable nameUser
        String nameUser = Txt_Name.getText();
        //Se le asigna lo que haya en el txt de paswword a la variable userPassowrd
        String userPassword = pss_Password.getText();
        //Se revisa que haya algo escrito en el txt osea un nameUser valido


                if (!logicFiles.PasswordValidation(userPassword) || nameUser.isEmpty()){
                    if(nameUser.isEmpty()) {
                        //Si no es valido se solicita uno valido
                        lb_infoUserName.setText("Please enter a valid username");
                        Txt_Name.setText("");

                    }
                    if (!logicFiles.PasswordValidation(userPassword)) {
                        lb_infoPassword.setText("""
                        La contraseña no se pudo guardar, revise que cumpla con los requisitos:\s
                          Mínimo de 8 caracteres\s
                          Contener mayúsculas y minúsculas
                          Al menos 1 número\s
                          Al menos 1 símbolo """);
                        pss_Password.setText("");
                    }

                } else if (!logicFiles.user_found(nameUser)) {
                    logicFiles.writeTextFile(nameUser,userPassword);
                    lb_infoPassword.setText("Guardado exitoso");
                    UserName = nameUser;
                    stage.setScene(getSeceneMenu());
                } else if (logicFiles.user_found(nameUser) && logicFiles.password_found(userPassword)) {

                    UserName = nameUser;
                    stage.setScene(getSeceneMenu());

                } else
                    lb_infoPassword.setText("Contraseña no valida");


    });
// Establece la alineación del VBox
    vBox_Welcome.setAlignment(Pos.CENTER);

    // se agrega cada objeto al Vbox
    vBox_Welcome.getChildren().addAll(labelWelcome,lb_nameInput,Txt_Name,lb_infoUserName,lb_passwordInput,pss_Password,lb_infoPassword,btt_Start);
    stage.centerOnScreen();


    BorderPane  borderPane_menu_and_welcome = new BorderPane();
    borderPane_menu_and_welcome.setTop(getVBoxMenu());
    borderPane_menu_and_welcome.setCenter(vBox_Welcome);

    return new Scene(borderPane_menu_and_welcome, 700, 700);
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
    Label labelCredits = creditsLabels("Créditos", 45);
    labelCredits.setAlignment(Pos.CENTER);
    Label labelCredit1 = creditsLabels("Proyecto desarrolado por: ",25);
    Label labelCredit2 = creditsLabels("""
            Alexander Fallas Sanabria C32838
            Jason Quesada Gómez C36213
            Fabian Quesada Cordero C36202""", 25);

    Label labelCredit3 = creditsLabels("Curso IF2000", 20);
    Label labelCredit4 = creditsLabels("Año: 2023", 20);
    Label labelCredit5 = creditsLabels("Taken Game", 20);
    Label labelCredit6 = creditsLabels("Ultima actualización: 29/11/2023", 20);
    Label labelCredit7 = creditsLabels("Tamaño: ", 20);
    Label labelCredit8 = creditsLabels("Versión: ", 20);
    Label labelCredit9 = creditsLabels("Desarrollado en JavaFx:", 20);

    Image backgroundImage = new Image("Fondo3.png.jpg");

    BackgroundImage backgroundImg = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(700, 700, true, true, true, true));

    Background background = new Background(backgroundImg);

    vBoxCredits.setBackground(background);


    // Agrega las etiquetas al contenedor VBox
    vBoxCredits.getChildren().addAll( labelCredits, labelCredit1, labelCredit2, labelCredit3,labelCredit5,labelCredit6,labelCredit7,labelCredit8,labelCredit9);

    // Crea la escena de créditos
    BorderPane  borderPane_menu_and_welcome = new BorderPane();
    borderPane_menu_and_welcome.setTop(getVBoxMenu());
    borderPane_menu_and_welcome.setCenter(vBoxCredits);

    return new Scene(borderPane_menu_and_welcome, 700, 700);
}
    //--------------------------------------------------------------------------------------------------------------

   //                                          Personalizador de etiquetas

    // Función para crear etiquetas con fuente y estilo personalizados
    public Label creditsLabels(String text, int fontSize) {
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
        vbx_pane.setSpacing(25);

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
        lb_Menu_Titulo.setFont(new Font("Kristen ITC",65));//Se le asigna la fuente al titulo

        //--------------------------------------------------------------------------------------------------------------

       //                                      Boton de Jugar

        //Se crea el botton del jugar
        Button btt_Menu_Jugar = new Button("Play");
        btt_Menu_Jugar.setFont(new Font("Comic Sans MS",27));

        btt_Menu_Jugar.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;"+ "-fx-text-fill: #000000;" +
                                "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFFFF;"); //Se le aplica color ;0

        btt_Menu_Jugar.setOnAction(actionEvent -> {
            stage.setTitle("Taken Game");
            stage.setScene(getSceneGame());
            stage.centerOnScreen();
        });


        //--------------------------------------------------------------------------------------------------------------

        //                                      Boton de Instrucciones

        Button btt_Menu_Instrucciones = new Button("Instructions");
        // Establece el estilo del botón de menú "Instrucciones"
        btt_Menu_Instrucciones.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" +
                "-fx-text-fill: #000000;" + "-fx-border-radius: 10;" + "-fx-background-radius: 10;"
                + "-fx-background-color: #FFFFFF;"
        );

        //fuente personalizada al botón
        btt_Menu_Instrucciones.setFont(new Font("Comic Sans MS", 26));

        // evento del botón para mostrar las instrucciones al hacer clic
        btt_Menu_Instrucciones.setOnAction(event -> {
            stage.setScene(mostrarInstrucciones());
            stage.setTitle("Instrucciones");
            stage.centerOnScreen();
        });




        //--------------------------------------------------------------------------------------------------------------

        //                                      Boton de Cerrar

        Button btt_Menu_Cerrar = new Button("Close");//Se crea el botton cerrar
        //Se le da estilo al boton cerrar
        btt_Menu_Cerrar.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
                                 "-fx-border-radius: 10;" + "-fx-background-radius: 10;" +
                                 "-fx-background-color: #FFFFFF;");

        btt_Menu_Cerrar.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton
        //Evento de close con el botton close
        btt_Menu_Cerrar.setOnAction(actionEvent -> {
            Platform.exit();//Sale
        });//Evento del boton


        //--------------------------------------------------------------------------------------------------------------
        //Centra_todo en el menu
        vbx_pane.setAlignment(Pos.CENTER);

        //Crea los hijos del vbx_pane
        vbx_pane.getChildren().addAll(lb_Menu_Titulo,btt_Menu_Jugar,btt_Menu_Instrucciones,btt_Menu_Cerrar);

        BorderPane  borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vbx_pane);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }


//----------------------------------------------------------------------------------------------------------------------

    //                                      Escena de instrucciones
    public Scene mostrarInstrucciones() {

    VBox instructionsPane = new VBox();
    instructionsPane.setAlignment(Pos.CENTER);
    instructionsPane.setSpacing(10);

    Label instructionLabels = new Label(
            """
                    1.The game consists of a 4X4 board, containing\s
                    16 numbers (0-15), with the objective of\s
                    arranging the numbers in order,starting at 1\s
                    and ending at 15 (empty space at the end).

                    2.Only the empty square is allowed to be moved
                    exchanging numbers with that
                    square until all of them are accommodated.

                    3.It is only allowed to move the number that is
                    vertically and horizontally to the empty
                    square any other movement, is invalid.

                    4.There is no time or move limit, so have fun\s
                    with the game.""");

        instructionLabels.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));
        instructionLabels.setStyle("-fx-text-fill: #000000");
        instructionLabels.setAlignment(Pos.CENTER);

    //contenedor vertical para las etiquetas de instrucciones



    // Configura el fondo de la escena con una imagen
    Image backgroundImage = new Image("Fondo3.png.jpg");

    BackgroundImage backgroundImg = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(700, 700, true, true, true, true));

    Background background = new Background(backgroundImg);

    instructionsPane.setBackground(background);



    instructionsPane.getChildren().addAll(instructionLabels);
    // Agrega el botón al contenedor de instrucciones

    // Configura la escena y muestra el escenario de instrucciones
        BorderPane  borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(instructionsPane);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }

//----------------------------------------------------------------------------------------------------------------------

    //                                      Cronometro

    // Método para detener el cronómetro y actualizar la variable de control
    public void detenerCronometro() {
        if (timeline != null) {
            timeline.pause(); // Pausa el cronómetro en lugar de detenerlo
            // Actualiza la variable de control del cronómetro
            cronometroEnEjecucion = false;
            cronometroEnPausa = true; // Indica que el cronómetro está en pausa
        }
    }

    // Método para inicializar el cronómetro
    public void inicializarCronometro(Label timeLabel) {
        // Verifica si el cronómetro no está en ejecución o está en pausa
        if (!cronometroEnEjecucion || cronometroEnPausa) {
            detenerCronometro(); // Pausa el cronómetro actual si hay uno en ejecución

            int[] segundosTranscurridos = {0};

            // Manejador de eventos que se ejecuta cada segundo
            EventHandler<ActionEvent> eventHandler = event -> {
                segundosTranscurridos[0]++; // Incrementa los segundos transcurridos cada segundo
                int minutos = segundosTranscurridos[0] / 60;
                int segundos = segundosTranscurridos[0] % 60;

                // Actualiza la etiqueta de tiempo
                timeLabel.setText("Time:" + minutos + "m " + segundos + "s");
            };

            // Crea un nuevo cronómetro si no existe uno
            if (timeline == null) {
                timeline = new Timeline(new KeyFrame(Duration.seconds(1), eventHandler));
                timeline.setCycleCount(Animation.INDEFINITE);
            }

            // Verifica si el cronómetro está en pausa
            if (cronometroEnPausa) {
                timeline.playFrom(Duration.seconds(segundosTranscurridos[0]));
                cronometroEnPausa = false;
            } else {
                timeline.play();
            }

            // Actualiza la variable de control del cronómetro
            cronometroEnEjecucion = true;
        }
    }

    // Método para reiniciar el cronómetro desde cero
    public void reiniciarCronometroDesdeCero(Label timeLabel) {
        detenerCronometro(); // Detener el cronómetro actual si hay uno en ejecución

        // Reiniciar variables
        int[] segundosTranscurridos = {0};

        // Manejador de eventos que se ejecuta cada segundo
        EventHandler<ActionEvent> eventHandler = event -> {
            segundosTranscurridos[0]++; // Incrementa los segundos transcurridos cada segundo
            int minutos = segundosTranscurridos[0] / 60;
            int segundos = segundosTranscurridos[0] % 60;

            // Actualiza la etiqueta de tiempo
            timeLabel.setText("Time: " + minutos + "m " + segundos + "s");
        };

        // Crear un nuevo cronómetro
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), eventHandler));
        timeline.setCycleCount(Animation.INDEFINITE);

        // Iniciar el cronómetro
        timeline.play();

        // Actualizar la variable de control del cronómetro
        cronometroEnEjecucion = true;
        cronometroEnPausa = false; // Restablecer el estado de pausa
    }



    //-----------------------------------------------------------------------------------------------------------------

    public Scene getSceneGame(){

        //Scene del juego



    //                                           Instancia del contenedor padre
      BorderPane borderPane = new BorderPane();//El contenedor padre contiene a todos los hijos de la scene

    //-----------------------------------------------------------------------------------------------------------------

        //                               Labels para el contador de movimientos
        //                                        y el cronometro

        // Crear un contenedor VBox para organizar verticalmente los elementos
        VBox vBox_left = new VBox();
      // Establece el estilo para labelMovimientos
        labelMovimientos.setStyle("-fx-text-fill: #000000; -fx-font-size: 20; -fx-font-weight: bold; " +
                "-fx-font-family: 'Comic Sans MS';");

      // Agrega labelMovimientos al contenedor VBox
        vBox_left.getChildren().add(this.labelMovimientos);
        vBox_left.setAlignment(Pos.CENTER);
        borderPane.setLeft(vBox_left);
        BorderPane.setMargin(vBox_left, new Insets(0, 25, 0, 25));

      // Establece el estilo para timeLabel
        timeLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20; -fx-font-weight: bold; " +
                "-fx-font-family: 'Comic Sans MS';");
      // Agregar timeLabel al contenedor VBox
        vBox_left.getChildren().add(timeLabel);




        //------------------------------------------------------------------------------------------------------------------

        //                                        Interfaz del juego(Los cuadrados)
        //                                      creacion del gridPane

      GridPane gridPane = new GridPane();//Cuadriculas del juego
      gridPane.setVgap(0.5);
      gridPane.setHgap(0.5);
      borderPane.setCenter(gridPane );//Se le asigna una posicion
        inicializarCronometro(timeLabel);

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
            button.setOnMouseClicked(new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // Obtiene la fila y la columna del botón presionado
                    int fila = Integer.parseInt((button.getProperties().get("fila")).toString());
                    int columna = Integer.parseInt((button.getProperties().get("columna")).toString());
                    // Verifica si la jugada es válida
                    if (generador.movimientoValido(fila, columna)) {
                        // Realiza el movimiento
                        generador.haceJugada(fila, columna);
                        // Incrementa el contador de movimientos
                        contadorMovimientos++;
                        // Actualiza el texto del contador de movimientos
                        labelMovimientos.setText("Movements:\n" + contadorMovimientos);
                        // Actualiza el stage
                        stage.setScene(getSceneGame());
                    } else {
                        // Cambiar el estilo del botón cuando el movimiento no es válido
                        if (finalI != 0) {
                            // Crea una imagen de que el botón presionado no se puede mover
                            Image img_2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Numero Invalido/" + finalI + ".png")));
                            ImageView imgV_2 = new ImageView(img_2);
                            button.setGraphic(imgV_2);
                        }
                        // Controla el tiempo en que la imagen de error se mantiene
                        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                        pauseTransition.setOnFinished(actionEvent -> {
                            // Después del tiempo se vuelve a la imagen anterior
                            button.setGraphic(imgV_1);
                        }); // end de qué hacer después de los dos segundos
                        // Inicia el tiempo
                        pauseTransition.play();
                    } // End del else
                    if (generador.juegoCompletado())
                        stage.setScene(getSceneWin());
                }
            });// End del evento

        }//End del for


        //-----------------------------------------------------------------------------------------------------------------

    //                                        Boton de cerrar

      HBox hBox_Bottom = new HBox();
        hBox_Bottom.setSpacing(20);

      Button btt_close = new Button("Close");

      btt_close.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

      btt_close.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
              "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF");



      btt_close.setMinSize(100,40);
        hBox_Bottom.getChildren().add(btt_close);
        hBox_Bottom.setAlignment(Pos.TOP_CENTER);
      borderPane.setBottom(hBox_Bottom);

      BorderPane.setMargin(hBox_Bottom,new Insets(0,0,50,0));

        // Evento del botón "Close"
        btt_close.setOnAction(actionEvent -> {
            // Guardar datos antes de cerrar el juego
            guardarDatosEnArchivo();
            Platform.exit();
        });
        //-----------------------------------------------------------------------------------------------------------------
        //                                       Botton de Volver al Menu
        Button btt_volverAlMenu = new Button("Back to menu");

        btt_volverAlMenu.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

        btt_volverAlMenu.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF");



        btt_volverAlMenu.setMinSize(100,40);
        hBox_Bottom.getChildren().add(btt_volverAlMenu);
        hBox_Bottom.setAlignment(Pos.TOP_CENTER);
        borderPane.setBottom(hBox_Bottom);

        btt_volverAlMenu.setOnAction(actionEvent -> {
            detenerCronometro();  // Detener el cronómetro al volver al menú
            stage.setScene(getSeceneMenu());
            stage.centerOnScreen();
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
        btt_Reiniciar_ring.setOnAction(actionEvent -> {
            // Detener y reiniciar el cronómetro
            reiniciarCronometroDesdeCero(timeLabel);
            // Reiniciar variables
            contadorMovimientos = 0;
            labelMovimientos.setText("Movements:\n0");
            // Reiniciar el juego
            generador.Board = new int[4][4];
            generador.StartBoard();
            MatrixGame = generador.Board;
            stage.setScene(getSceneGame());

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


        BorderPane  borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(borderPane);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }

    //------------------------------------------------------------------------------------------------------------------
    //                                Método para guardar los datos en un archivo

    public void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("juegosJugados.txt", true))) {
            //añaden los datos al archivo en el formato deseado
            //Obtiene la fecha y hora actuales
            LocalDateTime now = LocalDateTime.now();
            //Formatea la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            //escribe toda la informacion del jugador
            writer.write(UserName + ";"
                    + "Date: " + formattedDateTime + ";"
                    +  timeLabel.getText() + ";"
                    + " Movements: " + contadorMovimientos + "\n");
        } catch (IOException e) {
            System.out.println("Problemas con el archivo.");
        }
    }

    //----------------------------------------------------------------------------------------------------------------

    public Scene getSceneWin (){
        detenerCronometro();  // Detener el cronómetro al ganar el juego

        // -------------------------------------------------------------------------------------------------------------
        // se instancia el contendor//----------------------------------------
        //-----------------------  ---------------------------------------------------------------------------------------
        VBox vBox_Bottom = new VBox();// se le coloca una imagen como fondo a la escena
        Image img_fondo = new Image("Fondo.png");

        BackgroundImage bImg = new BackgroundImage(img_fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(700, 700, true, true, true, true));
        Background bGround = new Background(bImg);
        vBox_Bottom.setBackground(bGround);
        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        // se crea una label que indica que el juego se ha ganado
        Label labelWin = new Label("You win");
        labelWin.setStyle(
                "-fx-text-fill: #70fd00;" + // Color del texto
                        "-fx-font-family: 'Kristen ITC';" + // Fuente
                        "-fx-font-size: 80px;" + // Tamaño de fuente
                        "-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 0, 0);" // Efecto de sombra
        );
        vBox_Bottom.getChildren().add(labelWin);
        vBox_Bottom.setAlignment(Pos.CENTER);
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        // se crea un Vbox que contendra los botones
        vBox_Bottom.setSpacing(20); // espacio entre los botones

        // se crea un boton de volver a jugar
        Button btt_playAgain = new Button("Play Again");
        btt_playAgain.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

        btt_playAgain.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF"); // se le da un estilo al boton


        vBox_Bottom.getChildren().add(btt_playAgain);// se agrega al Vbox


        BorderPane.setMargin(vBox_Bottom,new Insets(0,0,50,0));

        btt_playAgain.setOnAction(actionEvent -> {

            guardarDatosEnArchivo(); // Guardar datos antes de volver a jugar
            // Detener y reiniciar el cronómetro
            reiniciarCronometroDesdeCero(timeLabel);
            // Reiniciar variables
            contadorMovimientos = 0;
            labelMovimientos.setText("Movements:\n0");
            generador.Board = new int[4][4];
            generador.StartBoard();
            MatrixGame = generador.Board;


            stage.setScene(getSceneGame());
            stage.centerOnScreen();

        });

        //--------------------------------------------------------------------------------------------------------------


        // se crea un boton de close
        Button btt_close = new Button("Close");

        btt_close.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton

        btt_close.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF");// se le da estilo



        btt_close.setMinSize(100,40);//tamaño
        vBox_Bottom.getChildren().add(btt_close);// se agrega al Vbox


        btt_close.setOnAction(actionEvent -> {
            guardarDatosEnArchivo();
            Platform.exit(); // cerrar la app
        });
        //--------------------------------------------------------------------------------------------------------------


        BorderPane  borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vBox_Bottom);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }


    public VBox getVBoxMenu(){

        VBox vBox_menu = new VBox();

        MenuBar menuBar = new MenuBar();

        Menu menu_Star = new Menu("Start");

        MenuItem menu_Create_Account = new MenuItem("Log in");
        menu_Create_Account.setOnAction(actionEvent -> stage.setScene(getSceneWelcome()));


        MenuItem menu_Change_Password= new MenuItem("Change Password");

        menu_Change_Password.setOnAction(actionEvent -> stage.setScene(getChangePassword()));


        MenuItem menu_exit = new MenuItem("Exit");


        menu_exit.setOnAction(actionEvent -> Platform.exit());

        menu_Star.getItems().addAll(menu_Create_Account,menu_Change_Password,menu_exit);



        Menu menu_Game = new Menu("Game");

        MenuItem menu_TAKEN_GAME = new MenuItem("TAKEN GAME");
        menu_TAKEN_GAME.setOnAction(actionEvent -> {

            if (logged_in)
                stage.setScene(getSceneGame());
            else
                System.out.println("Inicie sesión  antes");

        });

        menu_Game.getItems().addAll(menu_TAKEN_GAME);

        Menu menu_Reportes = new Menu("Reports");

        MenuItem menu_List_of_gamest = new MenuItem("List of games");
        menu_List_of_gamest.setOnAction(actionEvent -> stage.setScene(getGameList()));


        MenuItem menu_List_of_games_per_player= new MenuItem("List of games per player sorted by date");
        menu_List_of_games_per_player.setOnAction(actionEvent -> stage.setScene(getGamesByDateList()));


        MenuItem menu_List_of_the_best_10_players = new MenuItem("List of the best 10 players");
        menu_List_of_the_best_10_players.setOnAction(actionEvent -> stage.setScene(getTop10List()));


        menu_Reportes.getItems().addAll(menu_List_of_gamest,menu_List_of_games_per_player,menu_List_of_the_best_10_players);

        Menu menu_Ayuda = new Menu("Help");
        MenuItem menu_Credits = new MenuItem("Credits");
        menu_Credits.setOnAction(actionEvent -> stage.setScene(getSceneCredits()));

        MenuItem menu_Instructions= new MenuItem("Instructions");

        menu_Instructions.setOnAction(actionEvent -> stage.setScene(mostrarInstrucciones()));
        menu_Ayuda.getItems().addAll(menu_Credits,menu_Instructions);




        menuBar.getMenus().addAll(menu_Star,menu_Game,menu_Reportes,menu_Ayuda);

        vBox_menu.getChildren().addAll(menuBar);

        return vBox_menu;



    }

    public Scene getChangePassword() {
        // -------------------------------------------------------------------------------------------------------------
        VBox vBox_Welcome = new VBox(); // se instancia el contendor//
        vBox_Welcome.setSpacing(20); // espacio entre los objetos
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

        //--------------------------------------------------------------------------------------------------------------

        // se crea una label para solicitar el nombre al usuario
        Label lb_nameInput = new Label("Insert your nameUser");

        lb_nameInput.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
        lb_nameInput.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label





        // se crea un Txt que obtendra el name del usuario
        TextField Txt_Name = new TextField();
        // Establece el tamaño y la fuente del TextField
        Txt_Name.setFont(new Font("Comic Sans MS", 15));
        Txt_Name.setMaxSize(265,100); // tamaño del Txt



        // se crea una label para solicitar el password al usuario
        Label lb_passwordInput = new Label("Insert your new password");

        lb_passwordInput.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
        lb_passwordInput.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label


        // se crea un Txt que obtendra el password del usuario
        TextField TxT_Password = new TextField();
        // Establece el tamaño y la fuente del TextField
        TxT_Password.setFont(new Font("Comic Sans MS", 15));
        TxT_Password.setMaxSize(265,100); // tamaño del Txt



        // se crea un boton para que cuando el usuario ingrese su name, lo envie a los creditos y posteriormente al menu de inicio
        Button btt_Start = new Button("Change");
        btt_Start.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al botton
        btt_Start.setStyle("-fx-border-width: 2;" + "-fx-border-color: #000000;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;" + "-fx-background-color: #FFFF");// se le da estilo
        btt_Start.setMinSize(150, 60);// se le da tamaño


        //se crea una label para informar al usuario la situacion con la contraseña
        Label lb_infoPassword = new Label("");
        Label lb_infoUserName = new Label("");


        btt_Start.setOnAction(event -> {

            //Se le asigna lo que haya en el txt de username a la variable nameUser
            String nameUser = Txt_Name.getText();
            //Se le asigna lo que haya en el txt de paswword a la variable userPassowrd
            String userPassword = TxT_Password.getText();
            //Se revisa que haya algo escrito en el txt osea un nameUser valido

            if(logicFiles.user_found(nameUser)){
                logicFiles.changePassword(nameUser,userPassword);
                stage.setScene(getSeceneMenu());}
            else
                lb_infoUserName.setText("Ingrese un nameUser valido");
        });
// Establece la alineación del VBox
        vBox_Welcome.setAlignment(Pos.CENTER);

        // se agrega cada objeto al Vbox
        vBox_Welcome.getChildren().addAll(lb_nameInput,Txt_Name,lb_infoUserName,lb_passwordInput,TxT_Password,lb_infoPassword,btt_Start);
        stage.centerOnScreen();


        BorderPane  borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vBox_Welcome);

        return new Scene(borderPane_menu_and_welcome, 700, 700);

    }

    public Scene getGameList (){

        VBox vB_Gamelist = new VBox();
        vB_Gamelist.setSpacing(10);
        Label lb_GameListName = new Label("GAME LIST");
        lb_GameListName.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
        lb_GameListName.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label

        TableView tableGameList = new TableView();
        tableGameList.setEditable(false);

        TableColumn tc_playerName = new TableColumn("Player");
        tc_playerName.setPrefWidth(175);
        TableColumn tc_dateGame = new TableColumn("Date");
        tc_dateGame.setPrefWidth(175);
        TableColumn tc_durationTime = new TableColumn("Duration time");
        tc_durationTime.setPrefWidth(175);
        TableColumn tc_totalMovements = new TableColumn("Total movements");
        tc_totalMovements.setPrefWidth(175);

        tableGameList.getColumns().addAll(tc_playerName,tc_dateGame, tc_durationTime,tc_totalMovements);


        tc_playerName.setCellValueFactory(new PropertyValueFactory<>("player"));
        tc_dateGame.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_durationTime.setCellValueFactory(new PropertyValueFactory<>("durationTime"));
        tc_totalMovements.setCellValueFactory(new PropertyValueFactory<>("totalMovements"));
        tableGameList.setItems(logicFiles.getDataTableView());



        vB_Gamelist.getChildren().addAll(lb_GameListName, tableGameList);


        BorderPane borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vB_Gamelist);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }


    public Scene getGamesByDateList() {
        //Obtene los jugadores directamente de la lógica
        ObservableList<Player> players = logicFiles.getDataTableView();

        //Ordena los jugadores por fecha antes de mostrarlos
        Collections.sort(players, Comparator.comparing(Player::getDate).reversed());

        VBox vB_Gamelist = new VBox();
        vB_Gamelist.setSpacing(10);
        Label lb_GameListName = new Label("GAME LIST BY DATE");
        lb_GameListName.setFont(new Font("Comic Sans MS", 26));
        lb_GameListName.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;");

        TableView<Player> tableGameList = new TableView<>();
        tableGameList.setEditable(false);

        TableColumn<Player, String> tc_playerName = new TableColumn<>("Player");
        tc_playerName.setPrefWidth(175);
        TableColumn<Player, String> tc_dateGame = new TableColumn<>("Date");
        tc_dateGame.setPrefWidth(175);
        TableColumn<Player, String> tc_durationTime = new TableColumn<>("Duration time");
        tc_durationTime.setPrefWidth(175);
        TableColumn<Player, String> tc_totalMovements = new TableColumn<>("Total movements");
        tc_totalMovements.setPrefWidth(175);

        tableGameList.getColumns().addAll(tc_playerName, tc_dateGame, tc_durationTime, tc_totalMovements);

        tc_playerName.setCellValueFactory(new PropertyValueFactory<>("player"));
        tc_dateGame.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_durationTime.setCellValueFactory(new PropertyValueFactory<>("durationTime"));
        tc_totalMovements.setCellValueFactory(new PropertyValueFactory<>("totalMovements"));

        tableGameList.setItems(players);

        vB_Gamelist.getChildren().addAll(lb_GameListName, tableGameList);

        BorderPane borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vB_Gamelist);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }




    public Scene getTop10List (){
        //Obtene los jugadores directamente de la lógica
        ObservableList<Player> players = logicFiles.getDataTableView();
        //Ordena los jugadores por fecha antes de mostrarlos
        Collections.sort(players, Comparator.comparing(Player::getTotalMovements));
        if (players.size() > 10) {
            players = FXCollections.observableArrayList(players.subList(0, 10));
        }

        VBox vB_top10List = new VBox();
        vB_top10List.setSpacing(10);
        Label lb_top10ListName = new Label("TOP 10 PLAYERS");
        lb_top10ListName.setFont(new Font("Comic Sans MS",26));//Se le asigna la fuente al label
        lb_top10ListName.setStyle("-fx-border-width: 2;" + "-fx-text-fill: #000000;" +
                "-fx-border-radius: 10;" + "-fx-background-radius: 10;"); // se le da un estilo al label

        TableView tabletop10List = new TableView();
        tabletop10List.setEditable(false);

        TableColumn tc_playerName = new TableColumn("Player");
        tc_playerName.setPrefWidth(175);
        TableColumn tc_dateGame = new TableColumn("Date");
        tc_dateGame.setPrefWidth(175);
        TableColumn tc_durationTime = new TableColumn("Duration time");
        tc_durationTime.setPrefWidth(175);
        TableColumn tc_totalMovements = new TableColumn("Total movements");
        tc_totalMovements.setPrefWidth(175);

        tabletop10List.getColumns().addAll(tc_playerName,tc_dateGame, tc_durationTime,tc_totalMovements);


        tc_playerName.setCellValueFactory(new PropertyValueFactory<>("player"));
        tc_dateGame.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_durationTime.setCellValueFactory(new PropertyValueFactory<>("durationTime"));
        tc_totalMovements.setCellValueFactory(new PropertyValueFactory<>("totalMovements"));


        tabletop10List.setItems(players);

        vB_top10List.getChildren().addAll(lb_top10ListName, tabletop10List);


        BorderPane borderPane_menu_and_welcome = new BorderPane();
        borderPane_menu_and_welcome.setTop(getVBoxMenu());
        borderPane_menu_and_welcome.setCenter(vB_top10List);

        return new Scene(borderPane_menu_and_welcome, 700, 700);
    }
}
