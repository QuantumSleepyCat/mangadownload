package by.mangaloader.utils;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * Created by QuantumCat on 09.07.2017.
 */
public class DisableOptions {

    private ChoiceBox<Integer> tomsList;
    private ChoiceBox<Integer> chaptersList;
    private Button downloadTom;
    private Button downloadChapter;
    private Button downloadAll;
    private Button back;

    public DisableOptions(ChoiceBox<Integer> tomsList, ChoiceBox<Integer> chaptersList, Button downloadTom,
                          Button downloadChapter, Button downloadAll, Button back) {
        this.tomsList = tomsList;
        this.chaptersList = chaptersList;
        this.downloadTom = downloadTom;
        this.downloadChapter = downloadChapter;
        this.downloadAll = downloadAll;
        this.back = back;
    }

    public void enable()
    {
       tomsList.setDisable(false);
       chaptersList.setDisable(false);
       downloadTom.setDisable(false);
       downloadChapter.setDisable(false);
       downloadAll.setDisable(false);
       back.setDisable(false);
    }

    public void disable()
    {
        tomsList.setDisable(true);
        chaptersList.setDisable(true);
        downloadTom.setDisable(true);
        downloadChapter.setDisable(true);
        downloadAll.setDisable(true);
        back.setDisable(true);
    }

}
