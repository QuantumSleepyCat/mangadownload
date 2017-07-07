package by.mangaloader.download.image;

import by.mangaloader.parserattr.AttributesParser;
import by.mangaloader.utils.ErrorShow;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by QuantumCat on 07.07.2017.
 */
public class DownloadImage extends Thread{

    private int pageNumber;
    private File dirForManga;
    private String imageUrl;

    public DownloadImage(int pageNumber, File dirForManga, String imageUrl) {
        this.pageNumber = pageNumber;
        this.dirForManga = dirForManga;
        this.imageUrl = imageUrl;
    }


    @Override
    public void run() {

        try {
            URL url = new URL(imageUrl);
            BufferedImage image = null;
            image = ImageIO.read(url);
            String format = new AttributesParser().getImgFormat(imageUrl);
            ImageIO.write(image, format, new File(dirForManga,"Page_"+pageNumber+"."+format));
        } catch (IOException e) {
            ErrorShow.execute(e.getMessage());
        }

    }
}
