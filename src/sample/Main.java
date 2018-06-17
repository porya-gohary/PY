package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import jdk.internal.dynalink.beans.StaticClass;

import javax.management.Notification;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("PY Web Crawler");
        primaryStage.setScene(new Scene(root, root.minWidth(0), root.minHeight(0)));
        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);





    }

    public static void openlink2(String s){
        try {
            Desktop.getDesktop().browse(new URI(s));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
