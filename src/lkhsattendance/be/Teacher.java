/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LKHS
 */
public class Teacher {
    
    private int id;
    private String nameF;
    private String nameL;
    private List<Subject> subjectsTeaching = new ArrayList();
    private List<Clss> classesTeaching = new ArrayList();
    private String email;
    private String password;
    
    @Override
    public String toString(){
        return nameL + ", " + nameF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameF() {
        return nameF;
    }

    public List<Clss> getClassesTeaching() {
        return classesTeaching;
    }

    public void setClassesTeaching(List<Clss> classesTeaching) {
        this.classesTeaching = classesTeaching;
    }
    
    public void addClassesTeaching(Clss clss) {
        this.classesTeaching.add(clss);
    }
    

    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    

    public List<Subject> getSubjectsTeaching() {
        return subjectsTeaching;
    }

    public void setSubjectsTeaching(List<Subject> subjects) {
        this.subjectsTeaching = subjects;
    }

    public void addSubjectsTeaching(Subject subject) {
        this.subjectsTeaching.add(subject);
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
    
}

