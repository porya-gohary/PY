package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerThread extends Thread {
    Controller c;
    String URL;
    private HashSet<String> links;


    public WebCrawlerThread(Controller controller, String url){
        c=controller;
        URL=url;
        links = new HashSet<String>();

    }
    public void run() {

        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");
                //Elements linksOnPage = document.select("a");

                for (Element page : linksOnPage) {
                    //System.out.println(page.attr("abs:href"));

                    c.addMainUrl(page.attr("abs:href"));

                }
                c.Ready();


                //5. For each extracted URL... go back to Step 4.
//                for (Element page : linksOnPage) {
//                    getPageLinks(page.attr("abs:href"));
//                }
            } catch (IOException e) {
               // System.err.println("For '" + URL + "': " + e.getMessage());
                //this.stop();
                c.Error("Error : " + e.getMessage());
            }
        }
    }



}
