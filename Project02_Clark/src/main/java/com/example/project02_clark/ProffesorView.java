package com.example.project02_clark;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class ProffesorView {
    @FXML
    private TableView profTable;
    public <Proffesor> void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            String userLoggedIn = "Maxwell";
            while ((line = reader.readLine()) != null) {

                String[] userInfo = line.split(",");
                if(Objects.equals(userInfo[1], userLoggedIn)){
                    for (String item : userInfo){

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
