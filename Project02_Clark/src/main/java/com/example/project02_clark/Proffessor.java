package com.example.project02_clark;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Proffessor
{
    private String name;
    private String email;
    private String[] lectures;
    public Proffessor(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String[] getLectures() {
        return lectures;
    }

    public void setLectures(String[] lectures) {
        this.lectures = lectures;
    }

    public static ObservableList getDummyData() {
        ObservableList<Proffessor> people = FXCollections.observableArrayList();
        String[] availableClasses = {
                "Math", "English", "History", "Programming", "Yoga",
                "Art", "Forensic Science", "Philosophy"
        };
        var person1 = new Proffessor("jeff", "aol.com");
        people.add(person1);


        return people;
    }
}
