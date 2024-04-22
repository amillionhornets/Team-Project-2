package com.example.project02_clark;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfessorSignupController {

    @FXML
    private TextField nameField, emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signupButton;
    public Button backButton;

    // Method to handle signup action
    public void professorsignup() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Encrypts the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(name + "," + email + ","+ password+","+ hashedPassword);

        // Stores the user information in a file named users.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("professors.txt", true))) {
            writer.println(name + "," + email + "," + hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }


    }
    // Method for back button action
    public void back() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}