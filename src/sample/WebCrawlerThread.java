package sample;


import java.util.HashSet;

public class WebCrawlerThread extends Thread {
    Controller c;
    String URL;
    private HashSet<String> links;
    boolean zip;
    boolean exe;
    boolean pdf;
    boolean jpg;
    boolean png;
    boolean gif;
    int depth;
    int CurrentDepth = 1;


    public WebCrawlerThread(Controller controller, String url, boolean zip, boolean exe, boolean pdf, boolean jpg, boolean png, boolean gif, int depth) {
        c = controller;
        System.out.println(c);
        URL = "http://" + url;
        //URL =  url;
        links = new HashSet<String>();
        this.zip = zip;
        this.exe = exe;
        this.pdf = pdf;
        this.jpg = jpg;
        this.png = png;
        this.gif = gif;
        this.depth = depth;

    }

    public void run() {

            new FileDownloader(c, URL, zip, exe, pdf, jpg, png, gif,URL).root(URL);
            new WebCrawlerWithDepth(depth, c, zip, exe, pdf, jpg, png, gif,URL).getPageLinks(URL, -1);
            c.Ready();




    }

}



