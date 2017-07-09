package by.mangaloader.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by QuantumCat on 09.07.2017.
 */
public class ResultShow {

    public static void execute()
    {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Результат загрузки");
                alert.setContentText("Загрузка завершена. Если во время скачивания возникли ошибки, повторите загрузку.");
                alert.showAndWait();
            }
        });
    }

}


