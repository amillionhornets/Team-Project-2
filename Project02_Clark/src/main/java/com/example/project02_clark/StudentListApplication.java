package com.example.project02_clark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentListApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Student-List.fxml"));

        // Set the controller for the FXML file
        //loader.setController(new StudentListController());

        Parent root = loader.load();

        // Set up the primary stage
        primaryStage.setTitle("Student List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
