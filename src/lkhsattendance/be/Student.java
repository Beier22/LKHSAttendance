/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.be;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LKHS
 */
public class Student {
    
    private int id;
    private String nameL;
    private String nameF;
    private List<Date> daysAttendance = new ArrayList();
    private List<Date> daysAbsence = new ArrayList();
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

    @Override
    public String toString(){
        return nameL + ", " + nameF;
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

    public List<Date> getDaysAttendance() {
        return daysAttendance;
    }

    public void addDaysAttendance(Date date) {
        this.daysAttendance.add(date);
    }
    
    public void setDaysAttendance(List<Date> days) {
        this.daysAttendance = days;
    }
    public void addDaysAttendanceList(List<Date> date) {
        this.daysAttendance.add(date.get(0));
    }
    
    public List<Date> getDaysAbsence() {
        return daysAbsence;
    }
    public void addDaysAbsence(Date date) {
        this.daysAbsence.add(date);
    }
    public void setDaysAbsence(List<Date> days) {
        this.daysAbsence = days;
    }
    public void addDaysAbsenceList(List<Date> date) {
        this.daysAbsence.add(date.get(0));
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


