/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.bll;

import java.util.List;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;

/**
 *
 * @author LKHS
 */
public interface IModel {
    
    public List<Student> getAllStudents();
    public List<Teacher> getAllTeachers();
    public List<Subject> getAllSubjects();
    public Teacher getTeacher(Subject subject);
    public List<Student> getStudents(Subject subject);
    
}


