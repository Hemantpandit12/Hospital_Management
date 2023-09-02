package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto_datatransfer_object.ContactDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.ContactVo;

public class Contact extends HttpServlet{
	ContactVo vo;
	ContactDto dto;
	String name;
	String message;
	Connection connection;
	PreparedStatement ps;
	String query;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter pw=null;
		pw=response.getWriter();
		
		int result=0;
		
		//for request
		name=request.getParameter("name");
		message=request.getParameter("message");
		
		//vo
		vo=new ContactVo();
		vo.setName(name);
		vo.setMessage(message);
		
		//dto
		dto=new ContactDto();
		dto.setName(vo.getName());
		dto.setMessage(vo.getMessage());
		
		//jdbc connection code
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Prashant","Prashant");
			
			query="insert into contactmessage values(?,?)";
			ps=connection.prepareStatement(query);
			ps.setString(1,dto.getName());
			ps.setString(2,dto.getName());
			
			result=ps.executeUpdate();
			
			if(result!=0) {
				pw.println("<h1><center>Thank your "+name+"</center></h1>");
				pw.println("<h1><center>Your Request has been send Successfully</center></h1>");
				pw.println("<h1><center><a href='Contact.html'>Previous Page</a></center></h1>");
			}
			else {
				pw.println("<h1><center>Not Inserted</center></h1>");
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
