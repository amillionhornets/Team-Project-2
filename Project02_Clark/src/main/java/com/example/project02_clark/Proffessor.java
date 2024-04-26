package com.example.project02_clark;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Proffessor {
    private String name;
    private String email;
    private String lectures;
    public Proffessor(String name, String lectures) {
        this.name = name;
        this.lectures = lectures;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLectures() {
        return lectures;
    }

    public void setLectures(String lectures) {
        this.lectures = lectures;
    }

    public static ObservableList getDummyData() {
        ObservableList<Proffessor> people = FXCollections.observableArrayList();
        var person1 = new Proffessor("jeff", ProfessorLoginController.currentProf.getLectures());
        people.add(person1);


        return people;
    }
    private String[] getProfClasses(){
        try (BufferedReader reader = new BufferedReader(new FileReader("professors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length >= 3) {
                    String storedName = userInfo [0];


                    // Check if entered email and password match
                    if (storedName == ProfessorLoginController.currentProf.getName()) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String[0];
    }

}
