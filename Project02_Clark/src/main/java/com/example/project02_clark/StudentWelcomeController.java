package com.example.project02_clark;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentWelcomeController {

    @FXML
    private Label welcomeLabel;
    public Button backButton;

    // Method to set the welcome message
    public void setWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }
    // Method for back button action

    public void back() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}