/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.bll;

import java.sql.Date;
import java.util.List;
import lkhsattendance.be.Clss;
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
    public List<String> getUnattendedDays(Student student);
    public List<String> getAttendedDays(Student student);
    public List<Student> getUnattendingStudents(Date date, int classId);
    public List<Clss> getTeachingClasses(Teacher teacher);
    public void unattendance(Date date);
    public void login(int StudentID, Date date);
}


