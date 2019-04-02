/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lkhsattendance.bll.manager;

import java.sql.Date;
import java.util.List;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.dal.UserDAO;

/**
 *
 * @author alex
 */
public class Manager {

    UserDAO dao = new UserDAO();

    public void unattendance(Date date) {
        dao.unattendance(date);
    }

    public void login(int StudentID, Date date) {
        dao.login(StudentID, date);
    }
    
    public List<Student> getAllStudentsWithAttendance() {
        return dao.getAllStudentsWithAttendance();
    }
    
    public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        return dao.getAllTeachersWithClassesAndSubjects();
    }
    
}
