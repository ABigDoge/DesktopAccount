package model;

import controller.*;
import javafx.collections.ObservableList;
import java.sql.*;

public class MyPutManager {
	
	public static void store2sql(String username, String amount, String detail,String sort,int way) {
		
		 Connection connection = null;
		 Statement stmt = null;
		 String sql = null;
		 try {
				
		      connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
		      System.out.println("Opened database successfully");

		      stmt = connection.createStatement();
		      
		      if(way==1) {
		    	  sql = "INSERT INTO '"  + username +
			    		  "' (ID, AMOUNT, DETAIL, SORT) " +
		                   " VALUES (NULL, " + amount + ",'"
		                   + detail + "','" + sort + "');";
		    	  
		      }
		      else if(way==2) {
		    	  sql = "INSERT INTO '"  + username +
			    		  "' (ID, AMOUNT, DETAIL, SORT) " +
		                   " VALUES (NULL, -" + amount + ",'"
		                   + detail + "','" + sort + "');";
		      }
		    
		      stmt.executeUpdate(sql);
		      stmt.close();
		      connection.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("record created successfully");
		
	}
	
	public static void buildUser(String name,String psw,String sex) {
		
		Connection c = null;
	    Statement stmt1 = null;
	    Statement stmt2 = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
	      System.out.println("Opened database successfully");

	      stmt1 = c.createStatement();
	      stmt2 = c.createStatement();
	      String sql1 = "CREATE TABLE '" + name +
	                   "' (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
	                   " AMOUNT DOUBLE NOT NULL, " + 
	                   " DETAIL TEXT NOT NULL, " + 
	                   " SORT CHAR(10) NOT NULL, " +
	                   " date timestamp default (datetime('now','localtime')));" ;
	      stmt1.executeUpdate(sql1);
	      System.out.println("Table created successfully");
	      stmt1.close();
	      
	      String sql2 = "INSERT INTO user" +
	    		  " (ID, NAME, PASSWORD, SEX) " +
                  " VALUES (NULL, '" + name + "','"
                  + MD5.getMD5(psw) + "','" + sex + "');";
	      stmt2.executeUpdate(sql2);
	      stmt2.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
	}
	
	public static int findUser(String name,String psw) {
		
		Connection connection = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		int flag = 0;
		int hasuser=0;
		 try {
		      connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
		      System.out.println("Opened database successfully");

		      stmt1 = connection.createStatement();
		      stmt2 = connection.createStatement();
		      
		      String sql1 = "SELECT PASSWORD FROM user WHERE NAME = '" 
		    		  + name + "';";
		      String sql2 = "SELECT * FROM user;";
		      
		      ResultSet rs2 = stmt2.executeQuery(sql2);
		      while(rs2.next()) {
		    	  if(rs2.getString("NAME").equals(name)) {
		    		  hasuser=1;
		    		  break;
		    	  }
		      }
		      stmt2.close();
		      if(hasuser==0) {
		    	  
		    	  connection.close();
		    	  return 2;
		      }
		      
		      ResultSet rs = stmt1.executeQuery(sql1);
		      
		      String password = rs.getString("PASSWORD");
		      if(password.equals(MD5.getMD5(psw))) {  	  
		    	  flag=1;
		      }
		      else {
		    	  flag=0;
		      }
		      stmt1.close();
		 //     rs.close();
		      connection.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("find user successfully");
		    return flag;
		
	}
	
	public static void getPut(String username,int flag) {
		double money=0;
		Connection connection = null;
		try {
		
			connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
		
			Statement statement = connection.createStatement();
		//	System.out.println(username);
			System.out.println("open successfully");
			
			String sql = "select * from '" + username +"';";
			ResultSet rs = statement.executeQuery(sql);
			if(rs==null) {
				System.out.println("itsnull");
			}
			while (rs.next()) {
				money = rs.getDouble("AMOUNT");
				if( flag==1 && money<0 ) {
					continue;
				}
				else if( money<0 ) {
					money = -money;
				}
				else if( flag==0 && rs.getDouble("AMOUNT")>0 ) {
					continue;
				}
				MyPut user = new MyPut(rs.getString("DETAIL"),
						Double.toString(money),
						rs.getString("date"), rs.getString("SORT"));
			//	list.add(user);
			//	System.out.println(rs.getString("SORT"));
			}
			System.out.println("find account successfully");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		
	}
}
