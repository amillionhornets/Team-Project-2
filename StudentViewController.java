package ocu.edu.teamproj2;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentViewController {

    @FXML
    private TableView<Data> tableView;

    @FXML
    private TableColumn<Data, String> classNameColumn;

    @FXML
    private TableColumn<Data, String> crnColumn;

    @FXML
    private TableColumn<Data, String> professorColumn;

    @FXML
    private TableColumn<Data, Integer> chColumn;
//this edits the content for the cells in the table view
    @FXML
    public void initialize() {
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("Professor_Name"));
        classNameColumn.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        crnColumn.setCellValueFactory(new PropertyValueFactory<>("CRN_Num"));
        chColumn.setCellValueFactory(new PropertyValueFactory<>("CH_Num"));
    }

    private void readDataFromFile(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String Line;
            while ((Line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String professorName = parts[0];
                    int chNum = Integer.parseInt(parts[1]);
                    int crnNum = Integer.parseInt(parts[2]);
                    String className = parts[3];
                    tableView.getItems().add(new Data(professorName, chNum, crnNum, className));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


    class Data {
        private String Professor_Name;
        private int CH_Num;
        private int CRN_Num;
        private String Class_Name;

        public Data(String Professor_Name, int CH_Num, int CRN_Num, String Class_Name) {
            this.Professor_Name = Professor_Name;
            this.CH_Num = CH_Num;
            this.CRN_Num = CRN_Num;
            this.Class_Name = Class_Name;

        }

        public String getProfessor_Name() {
            return Professor_Name;
        }

        public void setProfessor_Name(String professor_Name) {
            this.Professor_Name = professor_Name;
        }

        public int getCH_Num() {
            return CH_Num;
        }

        public void setCH_Num(int CH_Num) {
            this.CH_Num = CH_Num;
        }

        public int getCRN_Num() {
            return CRN_Num;
        }

        public void setCRN_Num(int CRN_Num) {
            this.CRN_Num = CRN_Num;
        }

        public String getClass_Name() {
            return Class_Name;
        }

        public void setClass_Name(String class_Name) {
            this.Class_Name = class_Name;
        }
    }

