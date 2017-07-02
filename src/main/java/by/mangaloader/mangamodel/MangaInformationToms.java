package by.mangaloader.mangamodel;

import java.util.List;
import java.util.Map;

/**
 * Created by QuantumCat on 01.07.2017.
 */
public class MangaInformationToms {
    private int startTom;
    private int endTom;
    private String title;
    private String logoImage;
    private Map<Integer,List<Integer>> tomChaotersMap;

    public MangaInformationToms(int startTom, int endTom, String title, String logoImage, Map<Integer, List<Integer>> tomChaotersMap) {
        this.startTom = startTom;
        this.endTom = endTom;
        this.title = title;
        this.logoImage = logoImage;
        this.tomChaotersMap = tomChaotersMap;
    }

    public Map<Integer, List<Integer>> getTomChaotersMap() {
        return tomChaotersMap;
    }

    public int getStartTom() {
        return startTom;
    }

    public int getEndTom() {
        return endTom;
    }

    public String getTitle() {
        return title;
    }

    public String getLogoImage() {
        return logoImage;
    }
}
