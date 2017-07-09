package by.mangaloader.controllers;

import by.mangaloader.ApplicationEntryPoint;
import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import by.mangaloader.download.downloadmethods.DownloadCommand;
import by.mangaloader.download.downloadmethods.DownloadManga;
import by.mangaloader.download.downloadmethods.DownloadOneChapter;
import by.mangaloader.download.downloadmethods.DownloadTom;
import by.mangaloader.mangamodel.MangaInformationToms;
import by.mangaloader.utils.DisableOptions;
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
    private DisableOptions disableOptions;
    private DownloadCommand downloadCommandForAll;
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
    @FXML
    private Button stopDownload;

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


    public void stopDownloadAction()
    {
        downloadCommandForAll.stop();
    }

    public void downloadTomAction() throws IllegalAccessException, InstantiationException {
        downloadProgress.setVisible(true);
        stopDownload.setVisible(true);
        disableOptions.disable();
            DownloadCommand downloadCommand = new DownloadTom(mangaUrl + "/vol" + tomsList.getValue() + "/",
                    choseDirectory, mangaName, "vol" + tomsList.getValue() + "chapter",
                    mangaInformationToms.getTomChaotersMap().get(tomsList.getValue()), downloadProgress, stopDownload,disableOptions);
            downloadCommandForAll = downloadCommand;
            downloadCommand.execute();
    }

    public void downloadChapterAction() throws IllegalAccessException, InstantiationException {
        downloadProgress.setVisible(true);
        stopDownload.setVisible(true);
        disableOptions.disable();
        DownloadCommand downloadCommand = new DownloadOneChapter(mangaUrl + "/vol" + tomsList.getValue() + "/" + chaptersList.getValue(),
                choseDirectory,mangaName,"vol" + tomsList.getValue() + "chapter" + chaptersList.getValue(),
                downloadProgress,stopDownload,disableOptions);
        downloadCommandForAll=downloadCommand;
        downloadCommand.execute();
    }

    public void downloadAllAction() throws IllegalAccessException, InstantiationException {
        downloadProgress.setVisible(true);
        stopDownload.setVisible(true);
        disableOptions.disable();
        DownloadCommand downloadCommand = new DownloadManga(mangaUrl,
                choseDirectory,mangaName,mangaInformationToms.getTomChaotersMap(),downloadProgress,
                mangaInformationToms.getTomChaotersMap().keySet(),stopDownload,disableOptions);
        downloadCommandForAll=downloadCommand;
        downloadCommand.execute();
    }

    public void backAction() throws Exception {
        ApplicationEntryPoint applicationEntryPoint= new ApplicationEntryPoint();
        applicationEntryPoint.start((Stage) downloadTom.getScene().getWindow());
    }


    public void initialize(URL location, ResourceBundle resources) {
        downloadProgress.setVisible(false);
        stopDownload.setVisible(false);
        disableOptions = new DisableOptions(tomsList, chaptersList, downloadTom,
                downloadChapter, downloadAll, back);
    }
}
