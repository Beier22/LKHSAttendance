/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author LKHS
 */
public class DBAccess {
    SQLServerDataSource ds;
    
    public SQLServerDataSource DBAccess(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName(("Attendance2"));
        ds.setUser(("CS2018B_2"));
        ds.setPassword(("CS2018B_2"));
        ds.setServerName(("10.176.111.31"));
        ds.setPortNumber(((1433)));
        
        return ds;
    }
}

