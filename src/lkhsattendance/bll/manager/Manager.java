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
        return sDao.getAllStudentsWithAttendance();
    }
    
    public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        return tDao.getAllTeachersWithClassesAndSubjects();
    }
    
    public List<Clss> getAllClasses() {
        return tDao.getAllClasses();
    }
    
    
}
