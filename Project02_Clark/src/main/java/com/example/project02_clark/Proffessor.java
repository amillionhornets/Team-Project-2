package com.example.project02_clark;

public class Proffessor
{
    private String name;
    private String email;
    private String[] lectures;
    public Proffessor(String name, String[] lectures) {
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

    public String[] getLectures() {
        return lectures;
    }

    public void setLectures(String[] lectures) {
        this.lectures = lectures;
    }


}
