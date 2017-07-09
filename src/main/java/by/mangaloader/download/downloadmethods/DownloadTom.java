package by.mangaloader.download.downloadmethods;

import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import by.mangaloader.utils.DisableOptions;
import by.mangaloader.utils.ResultShow;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.List;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public class DownloadTom implements DownloadCommand {

    private String mangaURL;
    private String choseDir;
    private String mangaName;
    private String chapterDirName;
    private Thread thread;
    private List<Integer> chapterList;
    private ProgressBar progressBar;
    private Button stopBut;
    private DisableOptions disableOptions;

    public DownloadTom(String mangaURL, String choseDir, String mangaName, String chapterDirName,
                       List<Integer> chapterList, ProgressBar progressBar, Button stopBut,DisableOptions disableOptions) {
        this.mangaURL = mangaURL;
        this.choseDir = choseDir;
        this.mangaName = mangaName;
        this.chapterDirName = chapterDirName;
        this.chapterList = chapterList;
        this.progressBar = progressBar;
        this.stopBut=stopBut;
        this.disableOptions=disableOptions;
        thread = new Thread(this);
    }

    public void execute() {
        thread.start();
    }

    public void stop() {
        thread.stop();
        Platform.runLater(new Runnable() {
            public void run() {
                progressBar.setVisible(false);
                stopBut.setVisible(false);
            }
        });
        disableOptions.enable();
    }

    public void run() {

            for (int chapter : chapterList) {
                DownloadMethod downloadMethod = new DownloadChapter(mangaURL + chapter,
                        choseDir, mangaName, chapterDirName + chapter);
                downloadMethod.execute();
            }
        ResultShow.execute();
        Platform.runLater(new Runnable() {
            public void run() {
                progressBar.setVisible(false);
                stopBut.setVisible(false);
            }
        });
        disableOptions.enable();
    }
}
