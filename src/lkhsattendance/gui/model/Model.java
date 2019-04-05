/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.model;

import java.sql.Date;
import java.util.List;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;
import lkhsattendance.bll.manager.Manager;

/**
 *
 * @author LKHS
 */
public class Model implements IModel{

    Manager manager = new Manager();

    @Override
    public void unattendance(Date date) {
        manager.unattendance(date);
    }

    @Override
    public void login(int StudentID, Date date) {
        manager.login(StudentID, date);
    }
    
    @Override
    public List<Student> getAllStudentsWithAttendance() {
        return manager.getAllStudentsWithAttendance();
    }
    
    @Override
    public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        return manager.getAllTeachersWithClassesAndSubjects();
    }
    
    @Override
    public List<Clss> getAllClasses() {
        return manager.getAllClasses();
    }
    
    @Override
    public List<Subject> getAllSubjects() {
        return manager.getAllSubjects();
    }

    @Override
    public void removeTeacher(int teacherID) {
        manager.removeTeacher(teacherID);
    }

    @Override
    public void removeStudent(int studentID) {
        manager.removeStudent(studentID);
    }

    @Override
    public void createStudent(Student student) {
        manager.createStudent(student);
    }

    @Override
    public void createTecher(Teacher teacher) {
    manager.createTeacher(teacher);    
    }
    
}

