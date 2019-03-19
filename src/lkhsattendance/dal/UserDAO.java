/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;

/**
 *
 * @author mads_
 */
public class UserDAO {
    
    DBAccess dba = new DBAccess();
    SQLServerDataSource ds = dba.DBAccess();
    
    public List<Student> getAllStudents(){
        return null; //TODO
    }
    
    public List<Subject> getAllSubjects(){
        return null; //TODO
    }
    
}
