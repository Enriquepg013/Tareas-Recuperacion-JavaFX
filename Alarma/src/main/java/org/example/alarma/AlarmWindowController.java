package org.example.alarma;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class AlarmWindowController {

    @FXML
    private DialogPane dialogPane;

    @FXML
    private void closeAlarm() {

        if (dialogPane.getScene() != null) {
            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Error: La escena aún no está disponible.");
        }
    }
}
