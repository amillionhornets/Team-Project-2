package ocu.edu.teamproj2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class StudentViewController {

    @FXML
    private TableView<Data> tableView;

    @FXML
    private TableColumn<Data, String> classNameColumn;

    @FXML
    private TableColumn<Data, String> professorColumn;

    @FXML
    private TableColumn<Data, Integer> chColumn;

    @FXML
    private TableColumn<Data, Integer> crnColumn;

    // This method initializes the content for the cells in the table view
    @FXML
    public void initialize() {
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("Professor_Name"));
        classNameColumn.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        chColumn.setCellValueFactory(new PropertyValueFactory<>("CH_Num"));
        crnColumn.setCellValueFactory(new PropertyValueFactory<>("CRN_Num"));

        // Read professor names and class names from files
        ObservableList<String> professorNames = readLinesFromFile("professors.txt");
        ObservableList<String> classNames = readLinesFromFile("classes.txt");

        // Generate random numbers for CH_Num and CRN_Num
        Random rand = new Random();

        // Combine professor names, class names, and random numbers to create Data objects
        ObservableList<Data> data = FXCollections.observableArrayList();
        for (int i = 0; i < Math.min(professorNames.size(), classNames.size()); i++) {
            String professorName = professorNames.get(i);
            String className = classNames.get(i);
            int chNum = rand.nextInt(4) + 1; // Generate random number between 1 and 4 for CH_Num
            int crnNum = rand.nextInt(5000) + 1; // Generate random number between 1 and 5000 for CRN_Num
            data.add(new Data(professorName, chNum, crnNum, className));
        }

        // Set the data to the table view
        tableView.setItems(data);
    }

    // Method to read lines from a file and return them as an ObservableList
    private ObservableList<String> readLinesFromFile(String filePath) {
        ObservableList<String> lines = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Data class representing the structure of your data
    public static class Data {
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

        public int getCH_Num() {
            return CH_Num;
        }

        public int getCRN_Num() {
            return CRN_Num;
        }

        public String getClass_Name() {
            return Class_Name;
        }
    }
}
