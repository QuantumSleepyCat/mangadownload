package by.mangaloader.download.downloadmethods;

import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import by.mangaloader.utils.DisableOptions;
import by.mangaloader.utils.ResultShow;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public class DownloadOneChapter implements DownloadCommand{

    private String mangaURL;
    private String choseDir;
    private String mangaName;
    private String chapterDirName;
    private Thread thread;
    private ProgressBar progressBar;
    private Button stopBut;
    private DisableOptions disableOptions;
    private String tomname;

    public DownloadOneChapter(String mangaURL, String choseDir, String mangaName, String tomname,
                              String chapterDirName, ProgressBar progressBar,
                              Button stopBut,DisableOptions disableOptions) {
        this.mangaURL = mangaURL;
        this.choseDir = choseDir;
        this.mangaName = mangaName;
        this.tomname=tomname;
        this.chapterDirName = chapterDirName;
        this.progressBar = progressBar;
        this.stopBut=stopBut;
        this.disableOptions=disableOptions;
        this.thread = new Thread(this);
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
        DownloadMethod downloadMethod = new DownloadChapter(mangaURL,
                choseDir,mangaName, chapterDirName, tomname);
        downloadMethod.execute();
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
