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
 * @author LKHS
 */
public class Subject {
    
    private String name;
    private List<Integer> days; //Forslag: Måske vi kan lave en liste over hvilke ugedage i stedet for datoer?(fx 1-3-5 for mandag-onsdag-fredag)
    private List<Student> students;
    private Teacher teacher;
    //Evt tilføje classroom field, hvis vi tænker det kunne være relevant
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
}


