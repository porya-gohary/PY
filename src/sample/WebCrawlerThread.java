package sample;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerThread extends Thread {
    Controller c;
    String URL;
    private HashSet<String> links;
    String StrorageFolder="D:\\PY\\";


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
               // Elements png=document.select("img[src$=.jpg]");
                Elements png=document.select("img");
                //Elements files=document.select("a[href$=zip]");
                Elements files=document.select("link[href]");
                //Elements linksOnPage = document.select("a");
                for (Element file:files){
                    c.addFilesURL(file.attr("abs:href"));
                }

//                for(Element image : png) {
//                    System.out.println(image.attr("abs:src"));
//                    getFiles(image.attr("abs:src"));
//
//                }


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

    public void getFiles(String source)throws IOException{


        Connection.Response resultImageResponse = Jsoup.connect(source).ignoreContentType(true).execute();
        String strImageName =source.substring( source.lastIndexOf("/") + 1 );

        FileOutputStream out = (new FileOutputStream(new java.io.File(StrorageFolder +strImageName)));
        out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
        out.close();



    }



}
