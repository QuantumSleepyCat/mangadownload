package by.mangaloader.download;

import by.mangaloader.parserattr.AttributesParser;
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

    public boolean execute(String chapterUrl, String mangaDirname, String chapterDirname) throws IOException {
            initImageList(chapterUrl);
        File dirAdd = new File(mangaDirname,chapterDirname);
        if(!dirAdd.exists())
        {
            dirAdd.mkdirs();
        }
            BufferedImage image = null;//F:\program\test
            int counter=0;
            for(String imgUrl:imageListUrl) {
                counter++;
                URL url = new URL(imgUrl);
                image = ImageIO.read(url);
                String format = new AttributesParser().getImgFormat(imgUrl);
                ImageIO.write(image, format, new File(dirAdd,"Page_"+counter+"."+format));
            }

        return false;
    }

    public void initImageList(String chapterUrl) throws IOException {
        imageListUrl=new ArrayList<String>();
       Document document = Jsoup.connect(chapterUrl).get();
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
                     System.out.println(imgUrl);
                }
    }


}
