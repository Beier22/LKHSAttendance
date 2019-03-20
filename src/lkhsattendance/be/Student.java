/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.be;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mads_
 */
public class Student {
    
    private int id;
    private String nameL;
    private String nameF;
    private List<Date> days;
    private List<Subject> subjects;
    private String email;
    private String password;
    private int classId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    public List<Date> getDays() {
        return days;
    }

    public void setDays(List<Date> days) {
        this.days = days;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
    
    
}
