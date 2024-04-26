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
    ObservableList profs;

    public void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = "Maxwell";
            while ((line = reader.readLine()) != null) {

                String[] userInfo = line.split(",");
                if(Objects.equals(userInfo[1], userLoggedIn)){
//                    for (String item : userInfo){
//
//                    }

                }
                lecturesCol.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("name"));
                studentList.setCellValueFactory(new PropertyValueFactory<Proffessor, String>("lectures"));
                profs = Proffessor.getDummyData();
                profTable.setItems(profs);
            }
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
