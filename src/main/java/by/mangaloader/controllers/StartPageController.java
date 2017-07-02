package by.mangaloader.controllers;

import by.mangaloader.downloadtools.OpenDownloadTools;
import by.mangaloader.downloadtools.OpenNewWindow;
import by.mangaloader.getinformation.GetMainInformation;
import by.mangaloader.mangamodel.MangaInformationToms;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartPageController implements Initializable{

    private String siteUrl;


    @FXML
    private Button chooseDirectoryButton;
    @FXML
    private TextField choseDirectory;
    @FXML
    private ChoiceBox<String> choiceSite;
    @FXML
    private Button goToMangaDownloadOptions;
    @FXML
    private TextField mangaUrl;

    public void chooseDirectoryAction(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(chooseDirectoryButton.getScene().getWindow());
        System.out.println(selectedDirectory.getAbsolutePath());
        choseDirectory.setEditable(false);
        choseDirectory.setText(selectedDirectory.getAbsolutePath());
    }

    public void initialize(URL location, ResourceBundle resources) {
        List<String> siteList = new ArrayList<String>();
        siteList.add("ReadManga");
        siteList.add("MintManga");
        choiceSite.setItems(FXCollections.observableArrayList(siteList));
        choiceSite.setValue(choiceSite.getItems().get(0));
        siteUrl = "http://readmanga.me/";
     }

     public void getSiteListValue()
     {
         if(choiceSite.getValue().equals("ReadManga"))
         {
             siteUrl = "http://readmanga.me/";
         }
         else
         {
             siteUrl = "http://mintmanga.com/";
         }
     }

     public void goToMangaDownloadOptionsAction() throws IOException, IllegalAccessException, InstantiationException {
        getSiteListValue();
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         if(mangaUrl.getText().equals("") || choseDirectory.getText().equals(""))
         {
             alert.setHeaderText("Ошибка инициализации полей");
             alert.setContentText("Информция в одном из полей не указана!");
             alert.showAndWait();
             return;
         }
         GetMainInformation getMainInformation = new GetMainInformation();
         MangaInformationToms result = getMainInformation.execute(siteUrl+mangaUrl.getText());
         if(result!=null)
         {
             OpenNewWindow openNewWindow = OpenDownloadTools.class.newInstance();
             openNewWindow.execute(result,siteUrl+mangaUrl.getText(),
                     (Stage) choiceSite.getScene().getWindow(),"toolswindow.fxml",choseDirectory.getText(),mangaUrl.getText());
         }
         else
         {
             alert.setHeaderText("Ошибка получения информации");
             if(getMainInformation.isValid()) {
                 alert.setContentText("Данную мангу невозможно скачать!");
             }
             else
             {
                 alert.setContentText("Манга не найдена или вы отключены от сети Интернет!");
             }
             alert.showAndWait();
             return;
         }
     }
}
