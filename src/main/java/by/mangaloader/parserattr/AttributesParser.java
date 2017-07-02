package by.mangaloader.parserattr;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public class AttributesParser {

    public int tomParser(String href)
    {
        int tom =-1;
        String[] strsHref = href.split("/");
        for(String str: strsHref)
        {
            if(str.contains("vol"))
            {
                tom=Integer.valueOf(str.replace("vol",""));
            }
        }
        return tom;
    }

    public int chapterParser(String href)
    {
        String[] strsHref = href.split("/");
        return Integer.valueOf(strsHref[strsHref.length-1]);
    }

    public String getImgFormat(String url)
    {
        String[] imgs = url.split("\\Q.\\E");
        return imgs[imgs.length-1];
    }

}
