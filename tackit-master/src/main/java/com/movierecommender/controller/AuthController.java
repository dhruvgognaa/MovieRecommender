package com.movierecommender.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.movierecommender.commons.Statics;
import com.movierecommender.domain.User;
import com.sun.research.ws.wadl.Request;

@Path("/user")
public class AuthController {
	
	public DBConn conn;
	
	/**
	 * Sign up functionality for the user
	 * @param username
	 * @param password
	 * @param age
	 * @param gender
	 * @param occupation
	 * @return
	 * @throws IOException 
	 */
	@POST
	@Path("/signup")
	public Response userprofile(
		@FormParam("username") String username,
		@FormParam("password") String password,
		@FormParam("age") int age,
		@FormParam("gender") String gender, 
		@FormParam("occupation") String occupation,
		@FormParam("zipcode") String zipcode) throws IOException {
	
		User user = new User(username, password, age, occupation, gender, zipcode, 1);			
		conn = new DBConn();
		String userDetails = "";
		userDetails = conn.createUser(user);
		if(!userDetails.equals("")){
			updateUserInCSV(user, userDetails.split(";")[0].split(":")[1]);
		}
		return Response.status(200).entity(userDetails).build();

	}
	//943|22|M|student|77841
	private void updateUserInCSV(User user, String userid) throws IOException {
		// TODO Auto-generated method stub
		String toAppend = userid + "|"
				+ user.getAge() + "|"
				+ user.getGender().toUpperCase().charAt(0) + "|"
				+ user.getOccupation() + "|"
				+ user.getZipcode() + "\n";
		System.out.println("To append: " + toAppend);
		try{
			String csvFile = Statics.CSV_PATH + "data\\u.user";
			File file = new File(csvFile);
			System.out.println("file path: " + csvFile);
			System.out.println("to append: " + toAppend);
			System.out.println("file: " + file.getAbsolutePath());
			if(file.exists())
				System.out.println("file exists");
			FileWriter fileWritter = new FileWriter(file, true);
		    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		    bufferWritter.write(toAppend);
		    bufferWritter.flush();
		    bufferWritter.close();
		} catch (IOException e) {
			System.out.println("Couold not update user in file");
			e.printStackTrace();
		}
	}

	/**
	 * Login functionality for the user
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/login")
	public Response loginProfile(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
		@FormParam("username") String username,
		@FormParam("password") String password) throws ServletException, IOException {
		response.setContentType("text/html");
		username = request.getParameter("Name");
        password = request.getParameter("Pass");

		conn = new DBConn();
		String userDetails = "";
		userDetails = conn.getUser(username, password);

		return Response.status(200).entity(userDetails).build();
	}

}
