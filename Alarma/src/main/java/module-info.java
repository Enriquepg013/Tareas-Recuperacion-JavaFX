module org.example.alarma {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.alarma to javafx.fxml;
    exports org.example.alarma;
}