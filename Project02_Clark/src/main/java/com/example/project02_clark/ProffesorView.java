package com.example.project02_clark;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class ProffesorView {
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
    public void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = ProfessorLoginController.currentProf.getName();
            while ((line = reader.readLine()) != null) {
                lecturesCol.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("name"));
                studentList.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("lectures"));
                String[] userInfo = line.split(",");
                if(Objects.equals(userInfo[1], userLoggedIn)){
                    if(line.length() > 3){ // If they are teaching more than 1 lecture adds them to the table
                        Proffessor iterableProf = new Proffessor(userLoggedIn, userInfo[3]);
                        for (int i = 3; i < userInfo.length; i++) {
                            iterableProf.addLectures(new Proffessor(userLoggedIn, userInfo[i]));
                        }
                        profs = iterableProf.getLectureList();
                        profTable.setItems(profs);
                    }
                }else{ // Adds only the logged in prof which I think if faster than looping through 1 item and then adding the prof
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
}
