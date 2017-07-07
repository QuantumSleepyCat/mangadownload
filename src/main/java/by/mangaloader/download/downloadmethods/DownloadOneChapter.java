package by.mangaloader.download.downloadmethods;

import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import javafx.application.Platform;
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

    public DownloadOneChapter(String mangaURL, String choseDir, String mangaName, String chapterDirName, ProgressBar progressBar) {
        this.mangaURL = mangaURL;
        this.choseDir = choseDir;
        this.mangaName = mangaName;
        this.chapterDirName = chapterDirName;
        this.progressBar = progressBar;
        this.thread = new Thread(this);
    }

    public void execute() {
        thread.start();
    }

    public void run() {
        DownloadMethod downloadMethod = new DownloadChapter(mangaURL,
                choseDir,mangaName, chapterDirName);
        downloadMethod.execute();
        Platform.runLater(new Runnable() {
            public void run() {
                progressBar.setVisible(false);
            }
        });
    }
}
