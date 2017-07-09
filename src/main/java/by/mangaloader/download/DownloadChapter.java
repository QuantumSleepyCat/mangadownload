package by.mangaloader.download;

import by.mangaloader.download.image.DownloadImage;
import by.mangaloader.parserattr.AttributesParser;
import by.mangaloader.utils.ErrorShow;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public class DownloadChapter implements DownloadMethod {

    private List<String> imageListUrl;
    private String chapterUrl;
    private String mangaDirname;
    private String mangaNameDir;
    private String chapterDirname;


    public DownloadChapter(String chapterUrl, String mangaDirname, String mangaNameDir,  String chapterDirname)
    {
        this.chapterUrl=chapterUrl;
        this.mangaDirname=mangaDirname;
        this.mangaNameDir =mangaNameDir;
        this.chapterDirname=chapterDirname;
    }

    public void execute(){
        try {
            initImageList(chapterUrl);
            File dirManga = createDir(mangaDirname,mangaNameDir);
            File dirAdd = createDir(dirManga,chapterDirname);
            int counter=0;
            DownloadImage[] downloadImage = new DownloadImage[imageListUrl.size()];
            for(String imgUrl:imageListUrl) {
                counter++;
                downloadImage[counter-1] = new DownloadImage(counter,dirAdd,imgUrl);
                downloadImage[counter-1].start();
            }
            for (int i=0; i<downloadImage.length;i++)
            {
                downloadImage[i].join();
            }
        } catch (IOException e) {
            ErrorShow.execute(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ErrorShow.execute(e.getMessage());
        }
    }

    public void initImageList(String chapterUrl) throws IOException {
        imageListUrl=new ArrayList<String>();
       Document document = Jsoup.connect(chapterUrl+"?mature=1").get();
            Element options = document.select(".pageBlock.container.reader-bottom").first().select("script").first();
                int imgsPosit = options.html().indexOf("rm_h.init");
                String imgsAll = options.html().substring(imgsPosit);
                imgsAll = imgsAll.replace("rm_h.init( [[","");
                imgsAll = imgsAll.replace("], 0, false);","");

                String[] imgsStruct=imgsAll.split("\\[");
                for(String imgStr:imgsStruct)
                {
                     String[] attrImg = imgStr.split(",");
                     String imgUrl = attrImg[1].replace("'","")+
                             attrImg[0].replace("'","")+
                             attrImg[2].replace("\"","");
                     imageListUrl.add(imgUrl);
                    // System.out.println(imgUrl);
                }
    }


    public File createDir(String existingDir, String newDir)
    {
        File dir = new File(existingDir,newDir);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        return dir;
    }

    public File createDir(File existingDir, String newDir)
    {
        File dir = new File(existingDir,newDir);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        return dir;
    }

}
