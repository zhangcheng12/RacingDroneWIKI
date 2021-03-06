package com.RacingDroneWIKI.dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   private final static String DBDRIVER="com.mysql.jdbc.Driver";
   private static final String DBURL="jdbc:mysql://localhost:3306"
		   						+ "/racingdronewiki?characterEncoding=utf-8";
   private static final String DBUSER="root";
   private static final String DBPASS="";
   private static java.sql.Connection connection;
   private DatabaseConnection() {
		super();
	}
   public static java.sql.Connection getConnection() {
      // TODO: implement
	   try {
		   if(connection!=null)
			   if(!connection.isClosed())
				   return connection;
	        Class.forName(DBDRIVER); //classLoader,加载数据库驱动.
	        connection = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	    return connection;
   }
   
   public static void close() {
      // TODO: implement
	   try {
		   connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
}