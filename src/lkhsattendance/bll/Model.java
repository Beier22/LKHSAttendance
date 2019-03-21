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
import lkhsattendance.dal.UserDAO;

/**
 *
 * @author LKHS
 */
public class Model implements IModel{

    UserDAO dao = new UserDAO();
    
    @Override
    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    @Override
    public List<Subject> getAllSubjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher getTeacher(Subject subject) {
        return subject.getTeacher();
    }

    @Override
    public List<Student> getStudents(Subject subject) {
        return subject.getStudents();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return dao.getAllTeachers();
    }

    @Override
    public List<String> getUnattendedDays(Student student) {
        return dao.getUnattendedDays(student);
    }

    @Override
    public List<String> getAttendedDays(Student student) {
        return dao.getAttendedDays(student);
    }

    @Override
    public List<Student> getUnattendingStudents(Date date, int classId) {
        return dao.getUnattendingStudents(date, classId);
    }

    @Override
    public List<Clss> getTeachingClasses(Teacher teacher) {
        return dao.getTeachingClasses(teacher);
    }

    @Override
    public void unattendance(Date date) {
        dao.unattendance(date);
    }

    @Override
    public void login(int StudentID, Date date) {
        dao.login(StudentID, date);
    }
    
}

