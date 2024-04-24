package com.example.project02_clark;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ProffesorView {
    @FXML
    TableView<String> profTableView;
    @FXML
    TableColumn<Proffessor, String> lecturesCol;
    @FXML
    TableColumn<Proffessor, String> studentListCol;
    String signedInUser = ProfessorLoginController.currentProf.getName();
    public void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = "Maxwell";
            ObservableList<String> lectureList = FXCollections.observableArrayList();
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                List arrayListUserInfo = null;
                if(Objects.equals(userInfo[1], userLoggedIn)){
                    for (int i = 3; i < userInfo.length; i++){
                        arrayListUserInfo.set(0, userInfo[i]);
                        lectureList = FXCollections.observableList(arrayListUserInfo);
                        profTableView.setItems(lectureList);
                    }
                }

            }
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
