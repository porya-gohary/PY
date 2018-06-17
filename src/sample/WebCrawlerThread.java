package sample;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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



    public WebCrawlerThread(Controller controller, String url, boolean zip, boolean exe , boolean pdf , boolean jpg , boolean png, boolean gif,int depth){
        c=controller;
        URL="http://"+url;
        links = new HashSet<String>();
        this.zip=zip;
        this.exe=exe;
        this.pdf=pdf;
        this.jpg=jpg;
        this.png=png;
        this.gif=gif;
        this.depth=depth;

    }
    public void run() {
        new FileDownloader(c,URL,zip,exe,pdf,jpg,png,gif);




    }





}
