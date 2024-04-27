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
    private String lecture;

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    private String Pass;
    private ObservableList<Proffessor> lectureList = FXCollections.observableArrayList();
    public Proffessor(String name, String lectures) {
        this.name = name;
        this.lecture = lectures;
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
        return lecture;
    }

    public void setLectures(String lectures) {
        this.lecture = lectures;
    }

    public static ObservableList getData() {
        ObservableList<Proffessor> people = FXCollections.observableArrayList();
        var person1 = new Proffessor(ProfessorLoginController.currentProf.getName(), ProfessorLoginController.currentProf.getLectures());
        people.add(person1);


        return people;
    }
    public void addLectures(Proffessor professor){
        lectureList.add(professor);
    }
    public ObservableList<Proffessor> getLectureList(){
        return FXCollections.observableList(lectureList);
    }
}
