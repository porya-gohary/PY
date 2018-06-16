package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;



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
            new WebCrawler().getPageLinks(url.getText());
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




}
