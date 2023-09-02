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

public class CleaningDepartmentInsert extends HttpServlet {

	String id;
	String cleanername;
	String age;
	String gender;
	String address;
	String workname;
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
		cleanername=request.getParameter("cleanername");
		age=request.getParameter("age");
		gender=request.getParameter("gender");
		address=request.getParameter("address");
		workname=request.getParameter("email");
		postname=request.getParameter("postname");
		
		//jdbc code
		//load class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//load class
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			query="insert into cleaninsert values(?,?,?,?,?,?,?)";
			
			ps=connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2,cleanername);
			ps.setInt(3, Integer.parseInt(age));
			ps.setString(4, gender);
			ps.setString(5, address);
			ps.setString(6,workname);
			ps.setString(7, postname);
			
			result=ps.executeUpdate();
			
			if(result!=0) {
				pw.println("<h1><center> Thank You "+cleanername+" </center></h1>");
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
