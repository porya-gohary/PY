package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Controller {
    @FXML
    private TextField url;
    @FXML
    private MenuButton menuButton;
    @FXML
    private TextField key1;
    @FXML
    private TextField key2;
    @FXML
    private TextField key3;
    @FXML
    private ListView listView1;
    @FXML
     private ListView listView2;
    @FXML
    private ListView listView3;
    @FXML
    private Button closeBtn;
    @FXML
    private Button closeBtn2;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ImageView readyImage;
    @FXML
    private Label ReadyLabel;
    @FXML
    private Label WorkingLabel;
    @FXML
    private Label ErrorLabel;
    @FXML
    private ImageView ErrorImg;



    WebCrawlerThread webCrawlerThread;
    String ErrMsg;









    @FXML
    void urlSetText (ActionEvent actionEvent) throws IOException {
    //  key1.setText("Hello");

        if(url.getText().isEmpty()){
            Parent root = FXMLLoader.load(getClass().getResource("ErrorMessage.fxml"));

            Scene scene=new Scene(root);

            Stage stage =new Stage();



            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("About");
            stage.setScene(scene);
            root.setEffect(new DropShadow());
            stage.show();
        }
        else {
            //new WebCrawler().getPageLinks("http://www.google.com/");
            progressIndicator.setVisible(true);
            progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            ErrorLabel.setVisible(false);
            readyImage.setVisible(false);
            ErrorImg.setVisible(false);
            WorkingLabel.setVisible(true);
            ReadyLabel.setVisible(false);


            //new WebCrawler(this).getPageLinks(url.getText());
            //new WebCrawlerThread(this,url.getText()).start();
            webCrawlerThread =new WebCrawlerThread(this ,url.getText());
            webCrawlerThread.start();






        }
    }

    @FXML
    void clearItems (ActionEvent actionEvent){
        url.clear();
        key1.clear();
        key2.clear();
        key3.clear();
        listView1.getItems().clear();
        listView2.getItems().clear();
        listView3.getItems().clear();
    }

    @FXML
    void showAbout(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));

        Scene scene=new Scene(root);

        Stage stage =new Stage();



        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("About");
        stage.setScene(scene);
        root.setEffect(new DropShadow());
        stage.show();




    }

    @FXML
    void close(ActionEvent actionEvent){
        if(actionEvent.getSource()==closeBtn) {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        }
        if(actionEvent.getSource()==closeBtn2) {
            Stage stage = (Stage) closeBtn2.getScene().getWindow();
            stage.close();
        }

    }



    @FXML
    void exit (ActionEvent actionEvent){
        Platform.exit();
    }

    @FXML
    void addMainUrl(String string){
        listView3.getItems().add(string);


    }

    @FXML
    void addFilesURL(String url){
        listView2.getItems().add(url);
    }
    @FXML
    void Ready(){
        WorkingLabel.setVisible(false);
        progressIndicator.setVisible(false);
        ErrorLabel.setVisible(false);
        ErrorImg.setVisible(false);
        readyImage.setVisible(true);
        ReadyLabel.setVisible(true);


    }
    void Error(String s){
        ErrMsg=s;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WorkingLabel.setVisible(false);
                progressIndicator.setVisible(false);
                readyImage.setVisible(false);
                ReadyLabel.setVisible(false);
                ErrorLabel.setVisible(true);
                ErrorLabel.setText(ErrMsg);
                ErrorImg.setVisible(true);
            }
        });
        webCrawlerThread.stop();

    }

    @FXML
    void OpenLink(javafx.scene.input.MouseEvent event){


        if(event.getSource()==listView1&&(listView1.getSelectionModel().getSelectedItem()!=null))
            Main.openlink2((String) listView1.getSelectionModel().getSelectedItem());
        if(event.getSource()==listView2&&(listView2.getSelectionModel().getSelectedItem()!=null))
            Main.openlink2((String) listView2.getSelectionModel().getSelectedItem());
        if(event.getSource()==listView3 &&(listView3.getSelectionModel().getSelectedItem()!=null))
            Main.openlink2((String) listView3.getSelectionModel().getSelectedItem());








    }




}
