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
    String root;

    public FileDownloader(Controller controller, String url, boolean zip, boolean exe, boolean pdf, boolean jpg, boolean png, boolean gif,String root) {
        c = controller;
        URL = url;
        links = new HashSet<String>();
        this.zip = zip;
        this.exe = exe;
        this.pdf = pdf;
        this.jpg = jpg;
        this.png = png;
        this.gif = gif;
        this.root=root;

    }

    public void Download() {
        if (!links.contains(URL) && !URL.isEmpty() && (URL.contains(root) || !c.SubDomain.isSelected())) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();

                //3. Parse the HTML to extract links to other URLs


                //Download Files Fetch
                Elements jpgs = document.getElementsByTag("img");
                Elements pngs = document.getElementsByTag("img");
                Elements gifs = document.getElementsByTag("img");
                Elements pdfs = document.getElementsByTag("a");
                Elements exes = document.getElementsByTag("a");
                Elements zips = document.getElementsByTag("a");


                Elements files = document.select("a");


                if (zip) {
                    for (Element image : zips) {


                        if (image.attr("abs:href").endsWith(".zip")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                            getFiles(image.attr("abs:href"), "zip");

                        }


                    }
                }
                if (exe) {
                    for (Element image : exes) {


                        if (image.attr("abs:href").endsWith(".exe")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                            getFiles(image.attr("abs:href"), "exe");
                        }


                    }
                }

                if (pdf) {
                    for (Element image : pdfs) {


                        if (image.attr("abs:href").endsWith(".pdf")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                            getFiles(image.attr("abs:href"), "pdf");
                        }


                    }
                }


                for (Element image : jpgs) {


                    if (image.attr("abs:src").endsWith(".jpg")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                        c.addFilesURL(image.attr("abs:src"));
                        if (jpg) {
                            getFiles(image.attr("abs:src"), "jpg");
                        }

                    }
                    if (image.attr("abs:src").endsWith(".jpeg")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                        c.addFilesURL(image.attr("abs:src"));
                        if (jpg) {
                            getFiles(image.attr("abs:src"), "jpeg");
                        }

                    }


                }


                for (Element image : pngs) {

                    if (image.attr("abs:src").endsWith(".png")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                        c.addFilesURL(image.attr("abs:src"));
                        if (png) {
                            getFiles(image.attr("abs:src"), "png");
                        }
                    }


                }


                for (Element image : gifs) {


                    if (image.attr("abs:src").endsWith(".gif")&& !c.listView2.getItems().toString().contains(image.attr("abs:src"))) {
                        c.addFilesURL(image.attr("abs:src"));
                        if (gif) {
                            getFiles(image.attr("abs:src"), "gif");
                        }

                    }


                }


                for (Element file : files) {
                    String s = file.attr("abs:href");
                    if (s.endsWith(".exe") || s.endsWith(".deb") || s.endsWith(".zip") || s.endsWith(".rar") ||
                            s.endsWith(".msi") || s.endsWith(".png") || s.endsWith(".jpg") || s.endsWith(".gif") ||
                            s.endsWith(".ico") || s.endsWith(".pdf") || s.endsWith(".tar")) {
                        if( !c.listView2.getItems().toString().contains(file.attr("abs:href")))
                        c.addFilesURL(file.attr("abs:href"));
                    }
                }

//                for (Element page : linksOnPage) {
//                    //System.out.println(page.attr("abs:href"));
//
//                    c.addMainUrl(page.attr("abs:href"));
//
//
//                }
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

    public void root(String rootURL) {
        Document document = null;
        try {
            document = Jsoup.connect(rootURL).get();
        } catch (IOException e) {
            c.Error("Error : " + e.getMessage());
        }


        Elements linksOnPage = document.select("a[href]");

        for (Element page : linksOnPage) {
            //System.out.println(page.attr("abs:href"));

            c.addMainUrl(page.attr("abs:href"));


        }
    }
}
