package com.example.project02_clark;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.control.Alert;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentSignupController {

    @FXML
    private TextField nameField, emailField, BField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signupButton;
    @FXML
    private Label alertLabel;
    @FXML
    public Button backButton;

    // Method to handle signup action
    public void signup() {
        nameField.setPrefWidth(400);
        emailField.setPrefWidth(400);
        BField.setPrefWidth(400);
        passwordField.setPrefWidth(400);
        String name = nameField.getText();
        String email = emailField.getText();
        String BNumber = BField.getText();
        String password = passwordField.getText();

        // Check if any required field is empty
        if (name.isEmpty() || email.isEmpty() || BNumber.isEmpty() || password.isEmpty()) {
            alertLabel.setText("Some fields are blank!");
            return;
        }

        // Encrypts the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(name + "," + email + "," + password + "," + BNumber + "," + hashedPassword);

        // Stores the user information in a file named studentUsers.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("studentUsers.txt", true))) {
            writer.println(name + "," + email + "," + hashedPassword);
            alertLabel.setText("Signup Successful!");
        } catch (IOException e) {
            e.printStackTrace();
            // Handles the exception
        }
    }
    // Method for back button action
    public void back() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}