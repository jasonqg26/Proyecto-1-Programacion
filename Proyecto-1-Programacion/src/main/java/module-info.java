module com.example.proyecto1programacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto1programacion to javafx.fxml;
    exports com.example.proyecto1programacion;
}