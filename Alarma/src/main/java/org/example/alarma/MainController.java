package org.example.alarma;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {
    @FXML private StackPane alarmContainer;

    @FXML
    private void addNewAlarm() {
        try {
            alarmContainer.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alarm.fxml"));
            alarmContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}