/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
public class StudentDAO implements DAOFacade {

    DBAccess dba = new DBAccess();
    SQLServerDataSource ds = dba.DBAccess();

    @Override
    public void unattendance(Date date) {
        String sql = "INSERT INTO [Attendance2].dbo.Attendance (StudentID, yyyymmdd, Attendance) SELECT [Student].StudentID, ?, 0 FROM [Student] ";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            System.out.println("unattendance registration already complete for one or more students");
        }
    }

    @Override
    public void login(int StudentID, Date date) {
        String sql = "UPDATE [Attendance2].[dbo].[Attendance] SET attendance=1 WHERE StudentID=? AND yyyymmdd=?";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, StudentID);
            ps.setDate(2, date);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Student> getAllStudents() {
        try (Connection con = ds.getConnection()) {

            List<Student> students = new ArrayList();

            String sql = "SELECT * FROM Student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                Student student = new Student();
                student.setId(rs.getInt("StudentID"));
                student.setNameF(rs.getString("StudentFName"));
                student.setNameL(rs.getString("StudentLName"));
                student.setClassId(rs.getInt("StudentClassID"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("pass"));

                students.add(student);

            }

            return students;
        } catch (SQLException sqle) {
            System.out.println("Error, DAO, getAllStudents");
            sqle.printStackTrace();
            System.out.println();
        }

        return null;
    }

    @Override
    public List<Student> getAllUsersWithData() {
        try (Connection con = ds.getConnection()) {
            List<Student> students = getAllStudents();

            String sql = "SELECT s.StudentID, s.StudentLName, s.StudentFName, s.StudentClassID, s.email, s.pass, yyyymmdd, attendance FROM [Attendance2].[dbo].Student as s LEFT JOIN [Attendance2].[dbo].Attendance as a ON s.StudentID=a.StudentID ORDER BY s.StudentID, attendance";

           
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Calendar cal = Calendar.getInstance();
            while (rs.next()) {
                if (!(rs.getDate("yyyymmdd")==null)) {
                cal.setTime(rs.getDate("yyyymmdd"));
                if (!(cal.get(Calendar.DAY_OF_WEEK)==7 || cal.get(Calendar.DAY_OF_WEEK)==8)) 
                {
                    
                
                if (rs.getInt("attendance") == 0) {
                    for (Student student : students) {
                        if (student.getId() == rs.getInt("StudentID")) {
                            Date date = rs.getDate("yyyymmdd");

                            student.addDaysAbsence(date);

                        }
                    }

                } else if (rs.getInt("attendance") == 1) {
                    for (Student student : students) {
                        if (student.getId() == rs.getInt("StudentID")) {
                            Date date = rs.getDate("yyyymmdd");

                            student.addDaysAttendance(date);
                            
                        }
                    }
                }
                }}

            }
            return students;

        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


    @Override
    public void createStudent(Student s) {
        System.out.println("testtesttest");
        String sql = "INSERT INTO [Attendance2].[dbo].Student "
                + "(StudentLName, StudentFName, StudentClassID, email, pass) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getNameL());
            ps.setString(2, s.getNameF());
            ps.setInt(3, s.getClassId());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPassword());
            ps.addBatch();
            ps.executeBatch();
            System.out.println("Student has been successfully created");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public List<Clss> getAllClasses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Subject> getAllSubjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(int id) {
        String sql = "DELETE FROM [Attendance2].[dbo].[Student] WHERE StudentID = ?";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createTeacher(Teacher teacher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
