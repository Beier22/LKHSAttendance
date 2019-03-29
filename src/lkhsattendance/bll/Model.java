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
    public void unattendance(Date date) {
        dao.unattendance(date);
    }

    @Override
    public void login(int StudentID, Date date) {
        dao.login(StudentID, date);
    }
    
    @Override
    public List<Student> getAllStudentsWithAttendance() {
        return dao.getAllStudentsWithAttendance();
    }
    
    @Override
    public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        return dao.getAllTeachersWithClassesAndSubjects();
    }
    
}

