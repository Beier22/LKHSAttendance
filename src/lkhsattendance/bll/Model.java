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
import lkhsattendance.dal.UserDAO;

/**
 *
 * @author mads_
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
    
}
