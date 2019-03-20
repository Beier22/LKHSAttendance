/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;

/**
 *
 * @author LKHS
 */
public class UserDAO {
    
    DBAccess dba = new DBAccess();
    SQLServerDataSource ds = dba.DBAccess();
    
    public List<Student> getAllStudents(){
        try (Connection con = ds.getConnection()){
            List<Student> students = new ArrayList();
            String sql = "SELECT * FROM Student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("StudentID"));
                student.setNameF(rs.getString("StudentFName"));
                student.setNameL(rs.getString("StudentLName"));
                student.setClassId(rs.getInt("StudentClassID"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                students.add(student);
            }
            return students;
        } catch (SQLException sqle) {
            System.out.println("Database error (Students)");
        }
        
        return null;
    }
    
    public List<Subject> getAllSubjects(){
        try (Connection con = ds.getConnection()){
            //TODO
        } catch (SQLException sqle) {
            System.out.println("Database error (Subjects)");
        }
        
        return null;
    }
    
    public List<Teacher> getAllTeachers(){
        try (Connection con = ds.getConnection()){
            List<Teacher> teachers = new ArrayList();
            String sql = "SELECT * FROM Teacher";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("TeacherID"));
                teacher.setNameF(rs.getString("TeacherFName"));
                teacher.setNameL(rs.getString("TeacherLName"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPassword(rs.getString("password"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException sqle) {
            System.out.println("Database error (Teachers)");
        }
        
        return null;
    }
    
}

