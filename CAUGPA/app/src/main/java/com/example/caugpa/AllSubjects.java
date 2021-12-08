package com.example.caugpa;

import java.io.Serializable;

public class AllSubjects implements Serializable {
    public int id;
    public String subject;
    public int year;
    public int weight;
    public String major;
    public String majorSpecific;

    public AllSubjects(int year, String subject, int weight, String major, String majorSpecific, int id) {
        this.year = year;
        this.subject= subject;
        this.weight = weight;
        this.major = major;
        this.majorSpecific = majorSpecific;
        this.id = id;
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

//    public String getScore(){
//        return score;
//    }

    public String getMajor(){
        return major;
    }

    public String getMajorSpecific(){
        return majorSpecific;
    }

}
