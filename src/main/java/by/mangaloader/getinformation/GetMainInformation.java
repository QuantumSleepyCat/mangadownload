package by.mangaloader.getinformation;

import by.mangaloader.mangamodel.MangaInformationToms;
import by.mangaloader.parserattr.AttributesParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuantumCat on 01.07.2017.
 */
public class GetMainInformation implements GetInformation{
    private int startTom;
    private int endTom;
    private String title;
    private String logoImage;
    private boolean isValid=true;
    private Map<Integer,List<Integer>> tomChaotersMap;
    public MangaInformationToms execute(String url) throws IOException {
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e){
        isValid = false;
        return null;
    }
        Element span = document.select(".read-first").first();
        startTom = getTom(span);
        Element spanLast = document.select(".read-last").first();
        endTom = getTom(spanLast);
        if(startTom == -1)
        {
            isValid=true;
            return null;
        }
        logoInit(document);
        title = document.select("span.name").first().text()+" | "+ document.select("span.eng-name").first().text();
        if(endTom ==-1 && startTom!=-1)
        {
            endTom = startTom;
        }
        initTomsChaptersMap(document);
        return new MangaInformationToms(startTom,endTom,title,logoImage, tomChaotersMap);
    }

    public void initTomsChaptersMap(Document document) {
        tomChaotersMap = new HashMap<Integer, List<Integer>>();
        Elements tbBody = document.select(".expandable.chapters-link")
                .select("table")
                .select("tbody").first().select("tr");
        int tomCounter = endTom;
        List<Integer> chapters = new ArrayList<Integer>();
        for (Element trElement : tbBody) {
            Element aElemetn = trElement.select("td").first().select("a").first();
            String href = aElemetn.attr("href");
            int tom = new AttributesParser().tomParser(href);
            if (tom == tomCounter) {
                chapters.add(new AttributesParser().chapterParser(href));
            } else {
                tomChaotersMap.put(tomCounter, chapters);
                tomCounter=tom;
                chapters = new ArrayList<Integer>();
                chapters.add(new AttributesParser().chapterParser(href));
            }
            tomChaotersMap.put(startTom,chapters);
        }
    }

    private int getTom(Element element)
    {
        int tomNumber = -1;
        if(element !=null) {
            Element src = element.select("a[href]").first();
            if(src!=null)
            {
                if(src.tagName().equals("a"))
                {
                    String link = src.attr("href");
                            tomNumber=new AttributesParser().tomParser(link);
                    }
                }
                else
                {
                    return -1;
                }
            }
        else
        {
            return -1;
        }
        return tomNumber;
    }

    public void logoInit(Document document)
    {
        Element logoImg=document.select(".picture-fotorama").select("img").first();
        logoImage=logoImg.attr("src");
    }

    public boolean isValid() {
        return isValid;
    }

}
