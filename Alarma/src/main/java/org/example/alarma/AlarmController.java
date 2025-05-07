package org.example.alarma;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class AlarmController {

    private boolean alarmTriggered = false;

    @FXML private ComboBox<Integer> hourCombo;
    @FXML private ComboBox<Integer> minuteCombo;
    @FXML private ToggleButton toggleButton;
    @FXML private Label statusLabel;
    @FXML private Label alarmTimeLabel;

    private LocalTime alarmTime;
    private AnimationTimer timer;
    private Stage alarmStage;

    @FXML
    public void initialize() {
        setupTimeSelectors();
        setupTimer();
        alarmTimeLabel.setVisible(false);
    }

    private void setupTimeSelectors() {
        StringConverter<Integer> converter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return String.format("%02d", object);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        };

        hourCombo.getItems().addAll(IntStream.rangeClosed(0, 23).boxed().toList());
        hourCombo.setConverter(converter);
        hourCombo.setCellFactory(lv -> new ListCell<Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.format("%02d", item));
            }
        });
        hourCombo.getSelectionModel().select(0);

        minuteCombo.getItems().addAll(IntStream.rangeClosed(0, 59).boxed().toList());
        minuteCombo.setConverter(converter);
        minuteCombo.setCellFactory(lv -> new ListCell<Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.format("%02d", item));
            }
        });
        minuteCombo.getSelectionModel().select(0);
    }

    private void setupTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkAlarm();
            }
        };
    }


    @FXML
    private void toggleAlarm() {
        if (toggleButton.isSelected()) {
            activateAlarm();
        } else {
            deactivateAlarm();
        }
    }

    private void activateAlarm() {
        alarmTime = LocalTime.of(hourCombo.getValue(), minuteCombo.getValue());

        toggleButton.setText("Cancelar Alarma");
        alarmTimeLabel.setText(alarmTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        alarmTimeLabel.setVisible(true);
        statusLabel.setText("Alarma activada");

        if (timer != null) {
            timer.start();
            System.out.println("AnimationTimer iniciado.");
        } else {
            System.out.println("ERROR: timer es NULL.");
        }
    }


    private void deactivateAlarm() {
        timer.stop();
        toggleButton.setSelected(false);
        toggleButton.setText("Activar Alarma");
        alarmTimeLabel.setVisible(false);
        statusLabel.setText("Alarma desactivada");
        closeAlarmWindow();
    }

    private void checkAlarm() {
        LocalTime current = LocalTime.now().withSecond(0).withNano(0);

        if (alarmTime.equals(current) && !alarmTriggered) {
            alarmTriggered = true;
            triggerAlarm();
        } else if (!alarmTime.equals(current)) {
            alarmTriggered = false;
        }
    }


    private void triggerAlarm() {
        showAlarmWindow();
    }


    private void showAlarmWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/alarma/AlarmWindow.fxml"));
            Parent root = loader.load();

            alarmStage = new Stage();
            alarmStage.initModality(Modality.APPLICATION_MODAL);
            alarmStage.initStyle(StageStyle.UTILITY);
            alarmStage.setTitle("¡Alarma Sonando!");
            alarmStage.setScene(new Scene(root));
            alarmStage.setResizable(false);

            alarmStage.show();

        } catch (IOException e) {
            System.out.println("Error al abrir la ventana:");
            e.printStackTrace();
        }
    }


    private void closeAlarmWindow() {
        if (alarmStage != null) {
            alarmStage.close();
            alarmStage = null;
        }
    }
}