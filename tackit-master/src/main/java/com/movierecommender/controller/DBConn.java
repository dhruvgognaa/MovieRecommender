package com.movierecommender.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import com.movierecommender.domain.User;

public class DBConn {

	/**
	 * 
	 * @param user
	 * @return Success message
	 */
	public String createUser(User user){
	    String query;
	    String returnUser = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        //query = "INSERT into user (username, password, age, occupation, gender) values "
	        query = "INSERT into newuserdata (age, gender, occupation, zipcode, username, password, newuser) values "
	        		+ "('" 
	        		+ user.getAge() + "','"
	        		+ user.getGender().toUpperCase().charAt(0) + "','"
	        		+ user.getOccupation() + "','"
	        		+ user.getZipcode() +"','"
	        		+ user.getUsername() +"','"
	        		+ user.getPassword() +"',"
	        		+ "1"
	        		+ ")";
	        stmt.executeUpdate(query);
	        System.out.println("User Created Successfully");
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    returnUser = getUser(user.getUsername(), user.getPassword());
	    return returnUser;
	}
	
	/**
	 * Assigns a username to existing users in data set
	 */
	public void assignUserName(){
		String query = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        for(int i=1;i<944;i++){
	        	query = "Update newuserdata set username = '"+ i + "' where id = " + i;
	        	stmt.executeUpdate(query);
	        }
	        
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Assigns a password to existing users in data set
	 */
	public void assignPassword(){
		String query = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        query = "Update newuserdata set password = 'test'";
        	stmt.executeUpdate(query);
	        
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return userDetails - User Details as string
	 */
	public String getUser(String username, String password){
	    String query;
	    String userDetails = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        
	        //query = "Select * from user where username = '" + username + "' and password = '" + password + "'";
	        query = "Select * from newuserdata where username = '" + username + "' and password = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while(rs.next()){
	        	userDetails += "id:" + rs.getString("id") + ";";
	        	userDetails += "username:" + rs.getString("username") + ";";
	        	userDetails += "age:" + rs.getString("age") + ";";
	        	userDetails += "occupation:" + rs.getString("occupation") + ";";
	        	userDetails += "gender:" + rs.getString("gender") + ";";
	        	userDetails += "zipcode:" + rs.getString("zipcode") + ";";
	        	userDetails += "newuser:" + rs.getString("newuser") + ";";
	        }
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return userDetails;
	}
	
	/**
	 * Accepts long value to search for the user in database
	 * @param userid
	 * @return userDetails - user details as string
	 */
	public String getUser(long userid){
	    String query;
	    String userDetails = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        
	        query = "Select * from userdata where id = " + userid;
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while(rs.next()){
	        	userDetails += "id:" + rs.getString("id") + ";";
	        	userDetails += "age:" + rs.getString("age") + ";";
	        	userDetails += "gender:" + rs.getString("gender") + ";";
	        	userDetails += "occupation:" + rs.getString("occupation") + ";";
	        	userDetails += "zipcode:" + rs.getString("zipcode") + ";";
	        }
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("user: " + userDetails);
	    return userDetails;
	}

	/**
	 * Returns id of the movie that the user searched for
	 * @param moviename
	 * @return movieid - id of the movie or empty string if not found
	 */
	public int getMovieId(String moviename) {
		String query;
	    int movideid = 0;
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        
	        query = "Select * from iteminfo where movietitle like '%" + moviename + "%'";
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while(rs.next()){
	        	movideid = Integer.parseInt(rs.getString("itemid"));
	        }
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return movideid;
	}
	
	/**
	 * Return list of movies
	 * @param items
	 * @return movies - list of movies
	 */
	public ArrayList<String> getMoviesDetails(List<RecommendedItem> items){
		String query;
	    ArrayList<String> movies = new ArrayList<String>();
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "ferrari");
	        Statement stmt = (Statement) con.createStatement();
	        String inClause = "(";
	        int index = 0;
	        
	        for(index=0;index<items.size()-1;index++){
	        	inClause += items.get(index).getItemID() + ", ";
			}
	        inClause += items.get(index).getItemID() + ")";
	        System.out.println(inClause);
	        query = "Select * from iteminfo where itemid in " + inClause + " order by find_in_set(itemid, '" + inClause + "')";
	        ResultSet rs = stmt.executeQuery(query);
	        index = 0;
	        while(rs.next()){
	        	String movieInfo = rs.getString("itemid") + ":::" + rs.getString("movietitle") + ":::" + rs.getString("releasedate") + ":::" + rs.getString("imdburl");
	        	movies.add(index, movieInfo);
	        }
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return movies;
	}
}
