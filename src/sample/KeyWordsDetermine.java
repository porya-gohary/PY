package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class KeyWordsDetermine {

    public boolean Determining(String URL, String KeyWord) {
        try {
            if (URL.contains(KeyWord)) {
                return true;
            }
            Document document = Jsoup.connect(URL).get();
            Element body = document.body();

            if (body.text().contains(KeyWord)) {
                return true;
            }

        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
        return false;
    }
}
