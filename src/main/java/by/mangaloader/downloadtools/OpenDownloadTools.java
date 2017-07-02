package by.mangaloader.downloadtools;

import by.mangaloader.controllers.DownloadToolsController;
import by.mangaloader.mangamodel.MangaInformationToms;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public class OpenDownloadTools implements OpenNewWindow{
    public void execute(MangaInformationToms mangaInformationToms, String mangaUrl, Stage window, String sceneName,String dirName,String mangaName) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource(sceneName));
        Parent root = fxmlLoader.load();
        window.setScene(new Scene(root, 739 ,458));
        DownloadToolsController downloadToolsController = fxmlLoader.getController();
        downloadToolsController.setMangaInformationToms(mangaInformationToms);
        downloadToolsController.setMangaUrl(mangaUrl);
        downloadToolsController.setChoseDirectory(dirName);
        downloadToolsController.setMangaName(mangaName);
        downloadToolsController.initSceneObjects();
        window.show();
    }
}
