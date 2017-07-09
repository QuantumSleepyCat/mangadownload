package by.mangaloader.download.downloadmethods;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public interface DownloadCommand extends Runnable{
    void execute();
    void stop();
}
