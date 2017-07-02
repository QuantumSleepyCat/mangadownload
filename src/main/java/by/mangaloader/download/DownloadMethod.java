package by.mangaloader.download;

import java.io.IOException;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public interface DownloadMethod {
    boolean execute(String chapterUrl, String mangaDirname, String chapterDirname) throws IOException;
}
