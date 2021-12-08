package com.example.caugpa;

import java.io.Serializable;

public class MySubjects implements Serializable {
    public int id;
    public String subject;
    public int year;
    public String score;
    public int weight;
    public String major;
    public String majorSpecific;

    public MySubjects(int year, String subject, String score,int weight,String major, String majorSpecific,int id) {
        this.year = year;
        this.subject= subject;
        this.score = score;
        this.weight = weight;
        this.major = major;
        this.majorSpecific = majorSpecific;
        this.id = id;
    }

    public MySubjects(int year, String subject, String score,int weight,String major, String majorSpecific) {
        this.year = year;
        this.subject= subject;
        this.score = score;
        this.weight = weight;
        this.major = major;
        this.majorSpecific = majorSpecific;
    }
    public int getId(){
        return id;
    }
    public String getSubject() {
        return subject;
    }

    public int getYear(){
        return year;
    }

    public String getScore(){
        return score;
    }

    public String getMajor(){
        return major;
    }
    public int getWeight(){
        return weight;
    }

    public String getMajorSpecific(){
        return majorSpecific;
    }

}
