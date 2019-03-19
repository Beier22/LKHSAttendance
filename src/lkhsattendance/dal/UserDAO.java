/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
        try (Connection con = ds.getConnection()){
            //TODO
        } catch (SQLException sqle) {
            System.out.println("Error connecting to database");
        }
        
        return null;
    }
    
    public List<Subject> getAllSubjects(){
        try (Connection con = ds.getConnection()){
            //TODO
        } catch (SQLException sqle) {
            System.out.println("Error connecting to database");
        }
        
        return null;
    }
    
}
