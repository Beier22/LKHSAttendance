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


    public void unattendance(Date date);
    public void login(int StudentID, Date date);
    public List<Student> getAllStudentsWithAttendance();
    public List<Teacher> getAllTeachersWithClassesAndSubjects();
}


