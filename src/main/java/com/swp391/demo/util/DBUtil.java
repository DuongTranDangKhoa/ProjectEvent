/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lnhtr
 */
public class DBUtil {

//    public static Connection makeConnection() {
//        Connection conn = null;
//        try {
//
//            String dbURL = "jdbc:sqlserver://LAPTOP-LJ22SJA1\\TRUONG223;databaseName=EIPS;encrypt=true;trustServerCertificate=true;";
//            //String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
//            String user = "sa";
//            String pass = "12345";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            //// Class.forName("com.mysql.jdbc.Driver");
//            // DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            conn = DriverManager.getConnection(dbURL, user, pass);
//            //System.out.println("Connect to DB successfully");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return conn;
//    }
    public static Connection makeConnection() {
        Connection conn = null;
        try {

//            String dbURL = "jdbc:mysql://containers-us-west-62.railway.app:6357/railway";
            String dbURL = "jdbc:mysql://containers-us-west-194.railway.app:6974/railway";
            //String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
            String user = "root";
            String pass = "f59aQnm6BDvfVwNVNrnQ";
            Class.forName("com.mysql.jdbc.Driver");
            
//             DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, user, pass);
            //System.out.println("Connect to DB successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        System.out.println("This is to test if we can connect to SQLServer");
        Connection conn = makeConnection();
        DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
        System.out.println("Driver name: " + dm.getDriverName());
        System.out.println("Driver version: " + dm.getDriverVersion());
        closeConnection(conn);
    }
}
