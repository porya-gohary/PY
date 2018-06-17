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

public class FileDownloader {
    Controller c;
    String URL;
    private HashSet<String> links;
    boolean zip;
    boolean exe;
    boolean pdf;
    boolean jpg;
    boolean png;
    boolean gif;

    public FileDownloader(Controller controller, String url, boolean zip, boolean exe, boolean pdf, boolean jpg, boolean png, boolean gif) {
        c = controller;
        URL = url;
        links = new HashSet<String>();
        this.zip = zip;
        this.exe = exe;
        this.pdf = pdf;
        this.jpg = jpg;
        this.png = png;
        this.gif = gif;
        Download();
    }

    public void Download() {
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

                //Download Files Fetch
                Elements jpgs = document.getElementsByTag("img");
                Elements pngs = document.getElementsByTag("img");
                Elements gifs = document.getElementsByTag("img");
                Elements pdfs = document.getElementsByTag("a");
                Elements exes = document.getElementsByTag("a");
                Elements zips = document.getElementsByTag("a");


                //Elements pngs=document.select("a[href$=png]");
                //Elements files=document.select("a[href$=zip]");
                //Elements files=document.select("link[href]");

                Elements files = document.select("a");
                //Elements linksOnPage = document.select("a");
                if (zip) {
                    for (Element image : zips) {


                        if (image.attr("abs:href").endsWith(".zip")) {
                            getFiles(image.attr("abs:href"), "zip");
                        }


                    }
                }
                if (exe) {
                    for (Element image : exes) {


                        if (image.attr("abs:href").endsWith(".exe")) {
                            getFiles(image.attr("abs:href"), "exe");
                        }


                    }
                }

                if (pdf) {
                    for (Element image : pdfs) {


                        if (image.attr("abs:href").endsWith(".pdf")) {
                            getFiles(image.attr("abs:href"), "pdf");
                        }


                    }
                }


                if (jpg) {
                    for (Element image : jpgs) {


                        if (image.attr("abs:src").endsWith(".jpg")) {
                            getFiles(image.attr("abs:src"), "jpg");
                        }
                        if (image.attr("abs:src").endsWith(".jpeg")) {
                            getFiles(image.attr("abs:src"), "jpeg");
                        }


                    }
                }


                if (png) {
                    for (Element image : pngs) {


                        if (image.attr("abs:src").endsWith(".png")) {
                            getFiles(image.attr("abs:src"), "png");
                        }


                    }
                }

                if (gif) {
                    for (Element image : gifs) {


                        if (image.attr("abs:src").endsWith(".gif")) {
                            getFiles(image.attr("abs:src"), "gif");
                        }


                    }
                }


                for (Element file : files) {
                    String s = file.attr("abs:href");
                    if (s.endsWith(".exe") || s.endsWith(".deb") || s.endsWith(".zip") || s.endsWith(".rar") ||
                            s.endsWith(".msi") || s.endsWith(".png") || s.endsWith(".jpg") || s.endsWith(".gif") ||
                            s.endsWith(".ico") || s.endsWith(".pdf") || s.endsWith(".tar")) {
                        c.addFilesURL(file.attr("abs:href"));
                    }
                }

                for (Element page : linksOnPage) {
                    //System.out.println(page.attr("abs:href"));

                    c.addMainUrl(page.attr("abs:href"));


                }
                // c.Ready();


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

    public void getFiles(String source, String type) throws IOException {


        Connection.Response resultImageResponse = Jsoup.connect(source).ignoreContentType(true).execute();
        String strImageName = source.substring(source.lastIndexOf("/") + 1);


        File file = new File(c.StrorageFolder + type + "\\");
        if (!file.exists()) {
            // create the folder
            boolean result = file.mkdir();
            //System.out.println(result+"");
        }
        FileOutputStream out = (new FileOutputStream(new java.io.File(c.StrorageFolder + type + "\\" + strImageName)));
        out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
        out.close();


    }
}
