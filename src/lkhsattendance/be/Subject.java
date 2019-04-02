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
    
    private int id;
    private String name;
    private String description;
    private int teacherId = -1;
    private List<Integer> weekdays; //ikke udfyldt endnu
    


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }    
    @Override
    public String toString() {
        return description;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }
    
    
}


