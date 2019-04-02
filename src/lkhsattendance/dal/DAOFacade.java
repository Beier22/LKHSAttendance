/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lkhsattendance.dal;

import java.sql.Date;
import java.util.List;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;

/**
 *
 * @author alex
 */
public interface DAOFacade {

    public void unattendance(Date date);

    public void login(int StudentID, Date date);

    public List<Student> getAllStudentsWithAttendance();

    public List<Teacher> getAllTeachersWithClassesAndSubjects();
    
    public List<Clss> getAllClasses();
    
    public List<Subject> getAllSubjects();
    
}
