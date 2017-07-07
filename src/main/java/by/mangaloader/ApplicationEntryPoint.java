package by.mangaloader;

import by.mangaloader.download.DownloadChapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationEntryPoint extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Download manga");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 739, 458));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
