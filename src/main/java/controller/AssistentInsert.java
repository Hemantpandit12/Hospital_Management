package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AssistentInsert extends HttpServlet{

	String id;
	String assistentname;
	String age;
	String gender;
	String address;
	String email;
	String postname;
	Connection connection;
	PreparedStatement ps;
	String query;
	int result;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter pw=null;
		pw=response.getWriter();
		
		//for request
		id=request.getParameter("id");
		assistentname=request.getParameter("assistentname");
		age=request.getParameter("age");
		gender=request.getParameter("gender");
		address=request.getParameter("address");
		email=request.getParameter("email");
		postname=request.getParameter("postname");
		
		//jdbc code
		//load class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//load class
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			query="insert into assistentinsert values(?,?,?,?,?,?,?)";
			
			ps=connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2,assistentname);
			ps.setInt(3, Integer.parseInt(age));
			ps.setString(4, gender);
			ps.setString(5, address);
			ps.setString(6,email);
			ps.setString(7, postname);
			
			result=ps.executeUpdate();
			
			if(result!=0) {
				pw.println("<h1><center> Thank You "+assistentname+" </center></h1>");
				pw.println("<h1><center>Your Record has been Inserted Successfully</center></h1>");
				
			}
			else {
				pw.println("<h1><center>Not Inserted Successfully</center></h1>");
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.close();
			
		}
		
		
	}

}
