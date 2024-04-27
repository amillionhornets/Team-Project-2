package com.example.project02_clark;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.util.Objects;


public class ProffesorView {
    public TextField lectureTxtField;
    @FXML
    private TableView<Proffessor> profTable;
    @FXML
    TableColumn<Proffessor, String> lecturesCol;
    @FXML
    TableColumn<Proffessor, String> studentList;
    @FXML
    Button updateBtn;

    // prof stores the list if the professor only has 1 lecture and profs stores multiple lectures.
    ObservableList prof;
    ObservableList profs;
    // Reads the file and looks for the professor logged in then displalys all the lectures they teach

    public void initialize() {
        readFile();
        System.out.println("Worked");
    }
    public void readFile(){

        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = ProfessorLoginController.currentProf.getEmail();
            while ((line = reader.readLine()) != null) {
                lecturesCol.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("name"));
                studentList.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("lectures"));
                String[] userInfo = line.split(",");
                if(Objects.equals(userInfo[1], userLoggedIn)){
                    if(line.length() >= 3){ // If they are teaching more than 1 lecture adds them to the table
                        Proffessor iterableProf = new Proffessor(userLoggedIn, userInfo[3]);
                        for (int i = 3; i < userInfo.length; i++) {
                            iterableProf.addLectures(new Proffessor(userLoggedIn, userInfo[i]));
                            //lecturesTaught[i-3] = userInfo[i];
                        }
                        profs = iterableProf.getLectureList();
                        profTable.setItems(profs);
                    }
                }
                else{ // Adds only the logged in prof which I think if faster than looping through 1 item and then adding the prof
                    prof = Proffessor.getData();
                    profTable.setItems(prof);
                }
            }
    // catch and throw any errors when reading the file
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
    public void refreshTable(ActionEvent actionEvent) {
        profTable.getItems().clear();
        readFile();
        System.out.println("Worked");

    }
    private void showStudents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Student-List.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle window loading error
        }
    }

    public void switchToSudentPage(ActionEvent actionEvent) {
        showStudents();
    }

    public void addClass(ActionEvent actionEvent) throws IOException {
        String newLecture = lectureTxtField.getText();
        StringBuilder classesString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = ProfessorLoginController.currentProf.getEmail();
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if(Objects.equals(userInfo[1], userLoggedIn)){
                    for (int i = 3; i < userInfo.length; i++) {
                        if (!classesString.isEmpty()) {
                            classesString.append(",");
                        }
                        classesString.append(userInfo[i]);
                    }
                }
            }
            // catch and throw any errors when reading the file
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(classesString);

        deleteOldLectures(String.valueOf(classesString));

        classesString.append(",").append(newLecture);
        try (PrintWriter writer2 = new PrintWriter(new FileWriter("professors.txt", true))) {
            writer2.println(ProfessorLoginController.currentProf.getName() + "," + ProfessorLoginController.currentProf.getEmail() + "," + ProfessorLoginController.currentProf.getPass() + "," + classesString);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    // Deletes the old line and adds new line with new lectures
    private void deleteOldLectures(String classes) {

        String filePath = "professors.txt";
        String lineToRemove = ProfessorLoginController.currentProf.getName() + "," + ProfessorLoginController.currentProf.getEmail() + "," + ProfessorLoginController.currentProf.getPass() + "," + classes;
        // Try to write to a new file using the lines from the old one
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // If the current line is the line I don't want skip it
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        //Catch any read or write errors
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }
    // Make new file then append data and skip unwanted data
    private  void deleteLectureFromProf(String name, String partToRemove){
        try {
            File inputFile = new File("professors.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // If the current line is the line containing the string to be modified
                String[] userInfo = currentLine.split(",");
                if (Objects.equals(userInfo[1], name)) {
                    // Remove the desired part of the string
                    StringBuilder modifiedLine = new StringBuilder();
                    for (int i = 0; i < userInfo.length; i++) {
                        if (!Objects.equals(userInfo[i], partToRemove)) {
                            modifiedLine.append(userInfo[i]);
                            if (i < userInfo.length - 1 && !Objects.equals(userInfo[i + 1], partToRemove)) { // Add comma if not the last item and the next item is not to be removed
                                modifiedLine.append(",");
                            }
                        }
                    }
                    // Write the modified line to the temporary file
                    writer.write(modifiedLine + System.getProperty("line.separator"));
                } else {
                    // Write other lines as they are to the temporary file
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    // Delete record based off email
    public void deleteRecord(ActionEvent actionEvent) {
        ObservableList<Proffessor> selectedRow;
        selectedRow=profTable.getSelectionModel().getSelectedItems();
        System.out.println(selectedRow.get(0).getEmail());
        String partToRemove = selectedRow.get(0).getLectures();
        profTable.getItems().remove(profTable.getSelectionModel().getSelectedIndex());
        deleteLectureFromProf(selectedRow.get(0).getName(), partToRemove);
    }
}


