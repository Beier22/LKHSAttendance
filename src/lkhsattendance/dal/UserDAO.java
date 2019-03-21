/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lkhsattendance.be.Clss;
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
    
    public Student getStudentFromRS(ResultSet rs) {
        try {
            Student student = new Student();
            student.setId(rs.getInt("StudentID"));
            student.setNameF(rs.getString("StudentFName"));
            student.setNameL(rs.getString("StudentLName"));
            student.setClassId(rs.getInt("StudentClassID"));
            student.setEmail(rs.getString("email"));
            student.setPassword(rs.getString("password"));
            return student;
        } catch (SQLException ex) {
            System.out.println("Database error (Student ResultSet)");
        }
        return null;
    }

    public List<Student> getAllStudents(){
        try (Connection con = ds.getConnection()){
            List<Student> students = new ArrayList();
            String sql = "SELECT * FROM Student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                students.add(getStudentFromRS(rs));
            }
            return students;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Students)");
        }
        
        return null;
    }
    
    public List<Subject> getAllSubjects(){
        try (Connection con = ds.getConnection()){
            //TODO
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Subjects)");
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
            System.out.println("Database error (UserDAO, Teachers)");
        }
        
        return null;
    }
    
    public List<String> getUnattendedDays(Student student){
        try (Connection con = ds.getConnection()){
            List<String> dates = new ArrayList();
            String sql = "SELECT Date FROM Attendance WHERE StudentID = ? AND Attendance = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dates.add(rs.getString("Date"));
            }
            return dates;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Unattended)");
        }
        
        return null;
    }
    
    public List<String> getAttendedDays(Student student){
        try (Connection con = ds.getConnection()){
            List<String> dates = new ArrayList();
            String sql = "SELECT Date FROM Attendance WHERE StudentID = ? AND Attendance = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dates.add(rs.getString("Date"));
            }
            return dates;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Attended)");
        }
        
        return null;
    }
    
    public List<Student> getUnattendingStudents(Date date, int classId){
        try (Connection con = ds.getConnection()){
            List<Student> students = new ArrayList();
            String sql = "SELECT s.StudentID, s.StudentFName, s.StudentLName, s.StudentClassID, s.email, s.password\n"
                    + "FROM Student AS s \n"
                    + "JOIN Class as c ON s.StudentClassID = c.ClassID\n"
                    + "JOIN Attendance as a ON s.StudentID = a.StudentID\n"
                    + "WHERE c.ClassID = ? AND a.Date = ? AND a.Attendance = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                students.add(getStudentFromRS(rs));
            }
            return students;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Unattended Students)");
        }
        
        return null;
    }
    
    public List<Clss> getTeachingClasses(Teacher teacher) {
        try (Connection con = ds.getConnection()) {
            List<Clss> classes = new ArrayList();
            String sql = "SELECT c.ClassID, c.ClassName FROM Class as c\n"
                    + "JOIN Subject as sub ON c.ClassID = sub.ClassID\n"
                    + "WHERE sub.TeacherID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teacher.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Clss clss = new Clss();
                clss.setId(rs.getInt("ClassID"));
                clss.setName(rs.getString("ClassName"));
                classes.add(clss);
            }
            return classes;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Teaching Classes)");
        }

        return null;
    }
}

