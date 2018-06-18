package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class KeyWordsDetermine {

    public boolean Determining(String URL, String KeyWord) {
        try {
            if (URL.contains(KeyWord)) {
           //     System.out.println("True");
                return true;
            }
            Document document = Jsoup.connect(URL).get();
            Element body = document.body();

            if (body.text().contains(KeyWord)) {
               // System.out.println("True");
                return true;
            }
            if (KeyWord.contains("ک") || KeyWord.contains("ی") || KeyWord.contains("ه")) {
                KeyWord = KeyWord.replaceAll("ک", "ك");
                KeyWord = KeyWord.replaceAll("ی", "ي");
                KeyWord = KeyWord.replaceAll("ه", "ة");
               // System.out.println(KeyWord);

                if (URL.contains(KeyWord)) {
                  //  System.out.println("True");
                    return true;
                }

                if (body.text().contains(KeyWord)) {
                   // System.out.println("True");
                    return true;
                }
            } else if (KeyWord.contains("ك") || KeyWord.contains("ي") || KeyWord.contains("ة")) {
                KeyWord = KeyWord.replaceAll("ك", "ک");
                KeyWord = KeyWord.replaceAll("ي", "ی");
                KeyWord = KeyWord.replaceAll("ة", "ه");
                System.out.println(KeyWord);

                if (URL.contains(KeyWord)) {
                    return true;
                }

                if (body.text().contains(KeyWord)) {
                    return true;
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
       // System.out.println("False");
        return false;
    }
}
