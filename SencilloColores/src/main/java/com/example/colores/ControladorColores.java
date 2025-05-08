package com.example.colores;

import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControladorColores {

    @FXML
    private VBox root;

    @FXML
    private void cambiarARojo() {
        root.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }

    @FXML
    private void cambiarAVerde() {
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
    }

    @FXML
    private void cambiarAAzul() {
        root.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }
}
