package com.movierecommender.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.movierecommender.itemrecommend.ItemRecommendForUser;
import com.movierecommender.userrecommend.UserRecommend;

import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;


@Path("/recommendation")
public class RecommendationController {
	
	//connect to database and run corresponding queries.
	public DBConn conn;
	
	/**
	 * Accepts inputs from homepage.jsp
	 * Based on user id (>943 is considered a new user) calls the appropriate function 
	 * to get recommendations for the user
	 * @param moviename
	 * @param clustered
	 * @param type
	 * @param userid
	 * @param age
	 * @param gender
	 * @return recommendation list for the user.
	 */
	@POST 
	@Path("/getmovies")
	public Response movieid(
		@FormParam("moviename") String moviename,
		@FormParam("clustered") String clustered,
		@FormParam("item") String type,
		@FormParam("userid") long userid,
		@FormParam("age") int age,
		@FormParam("gender") String gender ) {
		
		conn = new DBConn();
		String userDetails;
		String [] info;
		String resultSet = "";
		int movieid = 0;
		ItemRecommendForUser iuser = new ItemRecommendForUser();
		UserRecommend uuser = new UserRecommend();

		boolean flag = true;
		List<RecommendedItem> items = null;
		
		if(userid>=1 && userid <= 943){
			userDetails = conn.getUser(userid);
			info = userDetails.split(";");
			age = Integer.parseInt(info[1].split(":")[1]);
			gender = info[2].split(":")[1];

			if(type.equals("itemtoitem") && clustered.equals("clustered"))
				items = iuser.runClustRecommend(age, gender, flag, userid);
			else if(type.equals("usertouser") && clustered.equals("clustered"))
				items = uuser.runClustRecommend(age, gender, flag, userid);
			else if(type.equals("itemtoitem") && clustered.equals("nonclustered"))
				items = iuser.runNonClustRecommend(age, gender, flag, userid);
			else
				items = uuser.runNonClustRecommend(age, gender, flag, userid);
		}
		else{
			movieid = conn.getMovieId(moviename);
			System.out.println("movieid: " + movieid);
			if(movieid != 0)
				items = iuser.recommendationForNewUser(movieid);
			else
				resultSet = moviename + " not found in database";
		}
		
		if(items != null){
			List<String> movies = conn.getMoviesDetails(items);
			int index = 0;
	        
	        for(index=0;index<movies.size()-1;index++){
	        	resultSet += movies.get(index) + ";";
			}
	        resultSet += movies.get(index);
		}
		
		return Response.status(200).entity(resultSet).build();
	}
	
	/**
	 * Accepts moviename entered by the user and returns its id
	 * @param moviename
	 * @return
	 */
	@POST 
	@Path("/findmovie")
	public Response findmoviebyname(
		@FormParam("moviename") String moviename ) {
		
		conn = new DBConn();
		int movieid = 0;
		String resultSet = "";
		movieid = conn.getMovieId(moviename);
		if(movieid != 0)
			resultSet = ""+movieid;
		
		return Response.status(200).entity(resultSet).build();
	}

}
