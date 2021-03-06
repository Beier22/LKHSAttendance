/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lkhsattendance.bll.manager;

import java.sql.Date;
import java.util.List;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;
import lkhsattendance.dal.DAOFacade;
import lkhsattendance.dal.StudentDAO;
import lkhsattendance.dal.TeacherDAO;

/**
 *
 * @author alex
 */
public class Manager {

    DAOFacade sDao = new StudentDAO();
    DAOFacade tDao = (DAOFacade) new TeacherDAO();

    public void unattendance(Date date) {
        sDao.unattendance(date);
    }

    public void login(int StudentID, Date date) {
        sDao.login(StudentID, date);
    }
    
    public List<Student> getAllStudentsWithAttendance() {
        return (List<Student>) sDao.getAllUsersWithData();
    }
    
    public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        return (List<Teacher>) tDao.getAllUsersWithData();
    }
    
    public List<Clss> getAllClasses() {
        return tDao.getAllClasses();
    }

    public List<Subject> getAllSubjects() {
        return tDao.getAllSubjects();
    }

    public void removeTeacher(Teacher teacher) {
        tDao.removeTeacher(teacher);
    }

    public void removeStudent(Student student) {
        sDao.removeStudent(student);
    }
    
    public boolean createStudent(Student student) {
        return sDao.createStudent(student);
    }
    
    public boolean createTeacher(Teacher teacher) {
        return tDao.createTeacher(teacher);
    }
}
