package org.aes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.aes.controller.IndexController;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {// javafx + fxml 获取ui
        //加载fxml文件
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/fxml/index.fxml"));
        //加载父结点
        Parent root = mainLoader.load();
        //获取Controller
        IndexController indexController = mainLoader.getController();
        //设置Stage属性
        primaryStage.setTitle("S-AES");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //显示Stage
        primaryStage.show();
    }
}