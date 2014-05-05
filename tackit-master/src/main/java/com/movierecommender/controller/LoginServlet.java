package com.movierecommender.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

/**
 * Servlet implementation class LoginServlet
 */
@Path("/newlogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DBConn conn;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Path("/newpath")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		System.out.println("username entered is: " + username);
		out.println("hello: " + username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userid = "";
		
		HttpSession session = request.getSession();
		session.setAttribute("error", null);
		
		conn = new DBConn();
		String userDetails = "";
		userDetails = conn.getUser(username, password);
		System.out.println("user details: " + userDetails);
		
		if(!userDetails.equals("")){
			String details[] = userDetails.split(";");
			username = details[1].split(":")[1];
			userid = details[0].split(":")[1];
			session.setAttribute("username", username);
			session.setAttribute("userid", userid);
			response.sendRedirect("homepage.jsp");
		}
		else{
			session.setAttribute("error", "Invalid username/password");
			request.setAttribute("error", "Invalid username/password");
			response.sendRedirect("login.jsp");
		}
	}

}
