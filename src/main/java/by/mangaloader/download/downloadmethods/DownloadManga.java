package by.mangaloader.download.downloadmethods;

import by.mangaloader.download.DownloadChapter;
import by.mangaloader.download.DownloadMethod;
import by.mangaloader.utils.DisableOptions;
import by.mangaloader.utils.ResultShow;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public class DownloadManga implements DownloadCommand{
    private String mangaURL;
    private String choseDir;
    private String mangaName;
    private Thread thread;
    private Map<Integer,List<Integer>> mapWithChapters;
    private ProgressBar progressBar;
    private Set<Integer> tomsSet;
    private Button stopBut;
    private DisableOptions disableOptions;

    public DownloadManga(String mangaURL, String choseDir, String mangaName, Map<Integer, List<Integer>> mapWithChapters,
                         ProgressBar progressBar, Set<Integer> tomsSet, Button stopBut,DisableOptions disableOptions) {
        this.mangaURL = mangaURL;
        this.choseDir = choseDir;
        this.mangaName = mangaName;
        this.mapWithChapters = mapWithChapters;
        this.progressBar = progressBar;
        this.tomsSet = tomsSet;
        this.stopBut=stopBut;
        this.disableOptions=disableOptions;
        thread=new Thread(this);
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
        for(int tom:tomsSet) {
            for (int chapter : mapWithChapters.get(tom)) {
                DownloadMethod downloadMethod = new DownloadChapter(mangaURL + "/vol" + tom + "/" + chapter,
                        choseDir, mangaName, "chapter" + chapter,"vol" + tom);
                downloadMethod.execute();
            }
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
