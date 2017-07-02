package by.mangaloader.downloadtools;

import by.mangaloader.mangamodel.MangaInformationToms;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public interface OpenNewWindow {
    void execute(MangaInformationToms mangaInformationToms, String mangaUrl, Stage window, String sceneName, String dirName, String mangaName) throws IOException;
}
