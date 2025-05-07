package com.example.bankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class BankGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BankGUI.fxml"));
        primaryStage.setTitle("Simulador de Transferencias Bancarias");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
