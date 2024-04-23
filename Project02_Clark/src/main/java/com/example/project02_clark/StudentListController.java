package com.example.project02_clark;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentListController {

    @FXML
    private TextField searchField;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label studentYearLabel;
    @FXML
    private Label coursesRegisteredLabel;

    // Method to handle the search action when the user presses Enter in the search field
    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText(); // Get the search term from the text field

        // Call a method to search for the student and update the labels with the student information
        searchAndUpdateLabels(searchTerm);
    }

    // Method to search for the student information based on the given search term
    private void searchAndUpdateLabels(String searchTerm) {
        // Add your logic here to search for the student information based on the searchTerm
        // For example, you can read from a file or query a database
        String filePath = "studentUsers.txt"; // Path to your file containing student data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each line in the file contains student information in the format: "Name,ID,Year,Courses"
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(searchTerm)) { // Check if the second part (ID) matches the search term
                    // Update the labels with the student information
                    studentNameLabel.setText(parts[0]); // Name
                    studentIdLabel.setText(parts[1]); // ID
                    studentYearLabel.setText(parts[2]); // Year
                    coursesRegisteredLabel.setText(parts[3]); // Courses
                    return; // Exit the method after finding the student
                }
            }
            // Clear the labels if student not found
            clearLabels();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Method to clear the labels
    private void clearLabels() {
        studentNameLabel.setText("");
        studentIdLabel.setText("");
        studentYearLabel.setText("");
        coursesRegisteredLabel.setText("");
    }
}
