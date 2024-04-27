package com.example.project02_clark;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfessorLoginController {

    public Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    public Button backButton;
    public static Proffessor currentProf;

    // Method to handle login action
    public void professorlogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Read user information from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length >= 3) {
                    String storedName = userInfo [0];
                    String storedEmail = userInfo[1];
                    String storedPasswordHash = userInfo[2];

                    // Check if entered email and password match
                    if (storedEmail.equals(email) && BCrypt.checkpw(password, storedPasswordHash)) {
                        // Successful login
                        // Store which prof is logged in and what they teach
                        currentProf = new Proffessor(storedName, userInfo[3]);
                        currentProf.setEmail(storedEmail);
                        currentProf.setPass(storedPasswordHash);
                        showProffesorView();
                        return;
                    }
                }
            }
            // If no match found, show an error message
            showAlert("Invalid credentials", "Invalid username or password.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file reading error
            showAlert("Error", "Failed to read user information.");
        }
    }

    // Method to display an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show the welcome window
    private void showProffesorView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProffesorView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle window loading error
            showAlert("Error", "Failed to load welcome window.");
        }
    }
    // Method for back button action
    public void back() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
