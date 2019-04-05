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
import java.util.logging.Level;
import java.util.logging.Logger;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
import lkhsattendance.be.Teacher;

/**
 *
 * @author alex
 */
public class TeacherDAO implements DAOFacade {
    
    DBAccess dba = new DBAccess();
    SQLServerDataSource ds = dba.DBAccess();
    
        @Override
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
                subject.setTeacherId(rs.getInt("TeacherID"));
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
    
    @Override
        public List<Teacher> getAllUsersWithData() {
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
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
        
    @Override
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

    @Override
    public void unattendance(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void login(int StudentID, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void createTeacher(Teacher teacher) {
        try (Connection con = ds.getConnection()) {

        String sql = "INSERT INTO [Attendance2].[dbo].Teacher "
                + "(TeacherLName, TeacherFName, email, pass) "
                + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, teacher.getNameL());
            ps.setString(2, teacher.getNameF());
            ps.setString(3, teacher.getEmail());
            ps.setString(4, teacher.getPassword());
            ps.addBatch();
            ps.executeBatch();
            System.out.println("Teacher has been successfully created");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        addTeacherSubjects(teacher);
    }
    
    public void addTeacherSubjects(Teacher teacher) {
        int id = -1;
        try (Connection con = ds.getConnection()) {
            String getID = "SELECT TeacherID FROM Attendance2.dbo.[Teacher] WHERE email = '"+teacher.getEmail()+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getID);
            while(rs.next())
                id = rs.getInt("TeacherID");
            for (int i = 0; i<teacher.getSubjectsTeaching().size(); i++){
                Subject s = teacher.getSubjectsTeaching().get(i);
                int subjectID = s.getId();
                
            
            String sql = "UPDATE Attendance2.dbo.[Subject] SET TeacherID=? WHERE SubjectID=?"; 
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, subjectID);
            ps.addBatch();
            ps.executeBatch();
            
            }
            System.out.println("Subjects has been successfully added");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
                
    }

    @Override
    public void removeTeacher(Teacher teacher) {
        String sql = "DELETE FROM [Attendance2].[dbo].[Teacher] WHERE TeacherID = ?";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teacher.getId());
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            System.out.println("Teacher is coupled to a subject - please remove from subject before deleting");
        }
    }

    @Override
    public void createStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} 
