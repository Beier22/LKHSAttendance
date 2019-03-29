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
import java.util.List;
import java.util.Map;
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

    public void login(int StudentID, Date date) {
        String sql = "UPDATE [Attendance2].[dbo].[Attendance] SET attendance=1 WHERE StudentID=? AND yyyymmdd=?";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, StudentID);
            ps.setDate(2, date);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Student> getAllStudentsWithAttendance() {
        try (Connection con = ds.getConnection()) {
            List<Student> students = getAllStudents();

            String sql = "SELECT s.StudentID, s.StudentLName, s.StudentFName, s.StudentClassID, s.email, s.pass, yyyymmdd, attendance FROM [Attendance2].[dbo].Student as s LEFT JOIN [Attendance2].[dbo].Attendance as a ON s.StudentID=a.StudentID ORDER BY s.StudentID, attendance";

           
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

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

            }
            return students;

        } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    

    public List<Subject> getAllSubjects() {
        try (Connection con = ds.getConnection()) {

            List<Subject> subjects = new ArrayList();

            String sql = "SELECT * FROM Subject";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                Subject subject = new Subject();
                subject.setId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("SubjectName"));
                subject.setDescription(rs.getString("SubjectDescription"));

                subjects.add(subject);

            }

            return subjects;
        } catch (SQLException sqle) {
            System.out.println("Error, DAO, getAllSubjects");
            sqle.printStackTrace();
            System.out.println();
        }

        return null;
    }

    public List<Teacher> getAllTeachers() {
        try (Connection con = ds.getConnection()) {
            List<Teacher> teachers = new ArrayList();
            String sql = "SELECT * FROM Teacher";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("TeacherID"));
                teacher.setNameF(rs.getString("TeacherFName"));
                teacher.setNameL(rs.getString("TeacherLName"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPassword(rs.getString("pass"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException sqle) {
            System.out.println("Database error (UserDAO, Teachers)");
        }

        return null;
    }
    
        public List<Teacher> getAllTeachersWithClassesAndSubjects() {
        try (Connection con = ds.getConnection()) {
            List<Teacher> teachers = getAllTeachers();
            List<Clss> classes = getAllClasses();
            List<Subject> subjects = getAllSubjects();
            String sql = "SELECT t.TeacherID, s.SubjectID, c.ClassID FROM [Attendance2].[dbo].Teacher AS t " +
"INNER JOIN [Attendance2].[dbo].[Subject] as s ON s.TeacherID=t.TeacherID " +
"INNER JOIN [Attendance2].[dbo].[Class] as c ON s.ClassID=c.ClassID " +
"ORDER BY TeacherID";

           
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) 
            {
                
                for (Teacher teacher : teachers) 
                {
                    if (rs.getInt("TeacherID")==teacher.getId()) 
                    {
                        for (Clss clss : classes) 
                        {
                            if(clss.getId()==rs.getInt("ClassID")) 
                            {
                                teacher.addClassesTeaching(clss);
                            }
                        }
                        for (Subject subject : subjects) 
                        {
                            if(subject.getId()==rs.getInt("SubjectID")) 
                            {
                                teacher.addSubjectsTeaching(subject);
                            }
                        }
                    }
                }
                
            }
            return teachers;

        } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
        
        public List<Clss> getAllClasses() {
        try (Connection con = ds.getConnection()) {

            List<Clss> classes = new ArrayList();

            String sql = "SELECT * FROM Class";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                Clss clss = new Clss();
                clss.setId(rs.getInt("ClassID"));
                clss.setName(rs.getString("ClassName"));
                clss.setYearEnrolled(rs.getInt("ClassYearEnrolled"));

                classes.add(clss);

            }

            return classes;
        } catch (SQLException sqle) {
            System.out.println("Error, DAO, getAllClasses");
            sqle.printStackTrace();
            System.out.println();
        }

        return null;
        }

}
