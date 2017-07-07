package by.mangaloader.controllers;

import by.mangaloader.ApplicationEntryPoint;
import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import by.mangaloader.download.downloadmethods.DownloadCommand;
import by.mangaloader.download.downloadmethods.DownloadOneChapter;
import by.mangaloader.mangamodel.MangaInformationToms;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public class DownloadToolsController implements Initializable{

    private MangaInformationToms mangaInformationToms;
    private String mangaUrl;
    private String mangaName;
    private String choseDirectory;
    @FXML
    private ImageView logoOfManga;
    @FXML
    private Label title;
    @FXML
    private ChoiceBox<Integer> tomsList;
    @FXML
    private ChoiceBox<Integer> chaptersList;
    @FXML
    private Button downloadTom;
    @FXML
    private Button downloadChapter;
    @FXML
    private Button downloadAll;
    @FXML
    private Button back;
    @FXML
    private ProgressBar downloadProgress;

    public String getChoseDirectory() {
        return choseDirectory;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public void setChoseDirectory(String choseDirectory) {
        this.choseDirectory = choseDirectory;
    }

    public MangaInformationToms getMangaInformationToms() {
        return mangaInformationToms;
    }

    public void setMangaInformationToms(MangaInformationToms mangaInformationToms) {
        this.mangaInformationToms = mangaInformationToms;
    }

    public void setMangaUrl(String mangaUrl) {
        this.mangaUrl=mangaUrl;
    }

    public void initSceneObjects()
    {
        logoOfManga.setImage(new Image(mangaInformationToms.getLogoImage()));
        title.setText(mangaInformationToms.getTitle());
        title.setWrapText(true);
        List<Integer> toms = new ArrayList<Integer>();
        for(int tom:mangaInformationToms.getTomChaotersMap().keySet())
        {
            toms.add(tom);
        }
        tomsList.setItems(FXCollections.<Integer>observableArrayList(toms));
        tomsList.setValue(tomsList.getItems().get(0));
        initChapterList(tomsList.getValue());
        tomsList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              initChapterList(tomsList.getItems().get((Integer) newValue));
            }
        });
    }



    public void initChapterList(int tom)
    {
        chaptersList.setItems(FXCollections.<Integer>observableArrayList(mangaInformationToms.getTomChaotersMap().get(tom)));
        chaptersList.setValue(chaptersList.getItems().get(0));
    }

    public void downloadTomAction() throws IllegalAccessException, InstantiationException {

//            for(int chapter:mangaInformationToms.getTomChaotersMap().get(tomsList.getValue())) {
//                DownloadMethod downloadMethod = DownloadChapter.class.newInstance();
//                downloadMethod.execute(mangaUrl + "/vol" + tomsList.getValue() + "/" + chapter,
//                        choseDirectory, mangaName + "\\vol" + tomsList.getValue() + "chapter" + chapter);
//            }
    }

    public void downloadChapterAction() throws IllegalAccessException, InstantiationException {
        downloadProgress.setVisible(true);
        DownloadCommand downloadCommand = new DownloadOneChapter(mangaUrl + "/vol" + tomsList.getValue() + "/" + chaptersList.getValue(),
                choseDirectory,mangaName,"vol" + tomsList.getValue() + "chapter" + chaptersList.getValue(),
                downloadProgress);
        downloadCommand.execute();
    }

    public void downloadAllAction() throws IllegalAccessException, InstantiationException {
//        DownloadMethod downloadMethod = DownloadChapter.class.newInstance();
//        for(int tom:mangaInformationToms.getTomChaotersMap().keySet()) {
//            for(int chapter:mangaInformationToms.getTomChaotersMap().get(tom)) {
//                downloadMethod.execute(mangaUrl + "/vol" + tom + "/" + chapter,
//                        choseDirectory, mangaName + "\\vol" + tom + "chapter" + chapter);
//            }
//        }
    }

    public void backAction() throws Exception {
        ApplicationEntryPoint applicationEntryPoint= new ApplicationEntryPoint();
        applicationEntryPoint.start((Stage) downloadTom.getScene().getWindow());
    }


    public void initialize(URL location, ResourceBundle resources) {
        downloadProgress.setVisible(false);
    }
}
