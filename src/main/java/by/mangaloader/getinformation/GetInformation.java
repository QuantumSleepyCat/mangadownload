package by.mangaloader.getinformation;

import by.mangaloader.mangamodel.MangaInformationToms;

import java.io.IOException;

/**
 * Created by QuantumCat on 02.07.2017.
 */
public interface GetInformation {
    MangaInformationToms execute(String url) throws IOException;
}
