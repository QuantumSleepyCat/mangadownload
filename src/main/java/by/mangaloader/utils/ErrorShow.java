package by.mangaloader.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public class ErrorShow {
    public static void execute(final String message)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ошибка скачивания");
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }
}
