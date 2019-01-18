package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBInit {
	
	public static void check4user() {
		
		int flag=0;
		Connection connection = null;
		 Statement stmt = null;
		 Statement stmt2=null;
		 String sql = null;
		 try {
				
		      connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
		      System.out.println("Opened database successfully");

		      stmt = connection.createStatement();
		      stmt2 = connection.createStatement();
		      
		      sql="select name from sqlite_master where type='table';";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()) {
		    	  if(rs.getString("name").equals("user")) {
		    		  flag=1;
		    		  break;
		    	  }
		      }
		      
		      if(flag==0) {
		    	  sql="CREATE TABLE user " +
		    		"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
		             " NAME char(20) NOT NULL, " + 
		    		 "PASSWORD char(20) NOT NULL," +
		             " SEX char(10) NOT NULL);" ; 
		    	  stmt2.executeUpdate(sql);
		      }
		      
		      stmt.close();
		      connection.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("check4user successfully");
		
	}

}
