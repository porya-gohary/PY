package sample;

import javafx.scene.DepthTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerWithDepth {
    private  int MAX_DEPTH = 2;
    private HashSet<String> links;
    Controller c;

    boolean zip;
    boolean exe;
    boolean pdf;
    boolean jpg;
    boolean png;
    boolean gif;
    boolean k=false;

    public WebCrawlerWithDepth(int MAX_DEPTH, Controller controller, boolean zip, boolean exe, boolean pdf, boolean jpg, boolean png, boolean gif) {
        links = new HashSet<>();
        c=controller;
        this.MAX_DEPTH=MAX_DEPTH;
        this.zip = zip;
        this.exe = exe;
        this.pdf = pdf;
        this.jpg = jpg;
        this.png = png;
        this.gif = gif;
    }

    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            if(c.ChKey1.isSelected()) {
                if(new KeyWordsDetermine().Determining(URL,c.key1.getText())) {
                    if(c.ChKey2.isSelected()) {
                        if(new KeyWordsDetermine().Determining(URL,c.key2.getText())) {
                            if(c.ChKey3.isSelected()) {
                                if(new KeyWordsDetermine().Determining(URL,c.key3.getText())) {
                                    if(c.ChKey4.isSelected()) {
                                        if(new KeyWordsDetermine().Determining(URL,c.key4.getText())) {
                                            System.out.println(">> Depth: " + depth + " [" + URL + "]");
                                            c.addPagesURL(">> Depth: " + (depth + 1) + " " + URL);
                                            k=true;
                                        }
                                    }else{
                                        System.out.println(">> Depth: " + depth + " [" + URL + "]");
                                        c.addPagesURL(">> Depth: " + (depth + 1) + " " + URL);
                                        k=true;
                                    }
                                }
                            }else{
                                System.out.println(">> Depth: " + depth + " [" + URL + "]");
                                c.addPagesURL(">> Depth: " + (depth + 1) + " " + URL);
                                k=true;
                            }
                        }
                    }else{
                        System.out.println(">> Depth: " + depth + " [" + URL + "]");
                        c.addPagesURL(">> Depth: " + (depth + 1) + " " + URL);
                        k=true;
                    }
                }
            }else{
                System.out.println(">> Depth: " + depth + " [" + URL + "]");
                c.addPagesURL(">> Depth: " + (depth + 1) + " " + URL);
                k=true;
            }
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                if(k) {
                    for (Element page : linksOnPage) {
                        new FileDownloader(c, page.attr("abs:href"), zip, exe, pdf, jpg, png, gif).Download();
                        getPageLinks(page.attr("abs:href"), depth);
                    }
                    k=false;
                }
            } catch (IOException e) {
               // System.err.println("For '" + URL + "': " + e.getMessage());
                c.Error("Error : " + e.getMessage());
            }
        }
        //else if( depth==MAX_DEPTH)  c.Ready();
    }
}