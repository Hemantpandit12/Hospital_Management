package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto_datatransfer_object.AdminLoginDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.AdminLoginVo;

public class AdminLogin extends HttpServlet{
		
	AdminLoginVo vo;
	AdminLoginDto dto;
	String id;
	String password;
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	String query;
	java.sql.Statement statement;
		
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		//for request
		
		id=request.getParameter("id");
		password=request.getParameter("password");
		
		
		//jdbc code
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			String query="select * from admin where id=? and password=?";
			
				ps=connection.prepareStatement(query);
				//vo
				vo=new AdminLoginVo();
				vo.setId(id);
				vo.setPassword(password);
				
				
				//dto
				dto=new AdminLoginDto();
				dto.setId(Integer.parseInt(vo.getId()));
				dto.setPassword(vo.getPassword());
				
				ps.setInt(1,dto.getId());
				ps.setString(2, dto.getPassword());
				
				rs=ps.executeQuery();
				
				
					
			if(rs.next()) {
				pw.println("<header><h1><center><a href='Admin.html'>Admin Access</a></center></h1></header>");
				
				
				pw.println("<h1><center> Thank You :"+dto.getId()+"</center></h1>");
				pw.println("<h1><center> Your Logged in Successfully :</center></h1>");
				
				
			}
			else {
				pw.println("<h1><center> Data Not Inserted </center></h1>");
			}
			
			pw.println("<footer><h1><center> <a href='AdminLogin.html'>Back</a> </center></h1></footer>");
			
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
