package com.codeconverter.fscodeconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SubCode extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SubCode.class.getResource("maket/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FireSec Code Converter");
        stage.setResizable(false);
        stage.getIcons().add(new Image(SubCode.class.getResourceAsStream("img/icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}