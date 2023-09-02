package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManagerEntry extends HttpServlet{
	String id=null; 
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter pw=null;
		pw=response.getWriter();
		Connection connection=null;
		
		String query=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		//for request
		id=request.getParameter("id");
		
		//jdbc code
		//load class
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//build connection
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			query="select * from admin where id=?";
			
			ps=connection.prepareStatement(query);
			
			ps.setInt(1,Integer.parseInt(id));
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				pw.println("<h1><center>You Succeed</center></h1>");
				pw.println("<h1><center><a href='Index.html'>Homepage</a></center></h1>");
			}
			else {
				pw.println("<h1><center>Try Again</center></h1>");
				pw.println("<h1><center><a href='Entry.html'>Back</a></center></h1>");
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
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
