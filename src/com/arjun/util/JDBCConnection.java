package com.arjun.util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 
 * @author ARJUN SINGH 
 *
 */
public class JDBCConnection {
    public static Connection getDBConnection(final String dataBaseName, final String dbUserName, final String dbPassword) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName + "?useSSL=false", dbUserName, dbPassword);
        } catch (Exception e) {
            System.out.println(e);
            return con;
        }
        return con;
    }

}


