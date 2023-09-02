package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto_datatransfer_object.UserLoginDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.UserLoginVo;

public class UserLogin extends HttpServlet{
	    
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
	
		res.setContentType("text/html");
		PrintWriter pw=null;
		String query=null;
		
		String name=null;
		String password=null;
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserLoginVo vo=null;
		UserLoginDto dto=null;
		
		
		res.setContentType("text/html");
		pw=res.getWriter();
		
		
		name=req.getParameter("name");
		password=req.getParameter("password");
		
		
		//create vo object
		vo=new UserLoginVo();
		vo.setName(name);
		vo.setPassword(password);
		
		//create dto object
		dto=new UserLoginDto();
		dto.setName(vo.getName());
		dto.setPassword(vo.getPassword());
		
		
		
		
		//jdbc code
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		//load class
		connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
		
		query="select * from createaccount where name=? and password=?";
		
		ps=connection.prepareStatement(query);
		
		ps.setString(1, dto.getName());
		ps.setString(2, dto.getPassword());
		
		rs=ps.executeQuery();
		

		
		if(rs.next()) {
			
		pw.println("<h1><center>Name :"+rs.getString(1)+"</center></h1>");
		pw.println("<h1><center>Age :"+rs.getInt(2)+"</center></h1>");
		pw.println("<h1><center>Gender :"+rs.getString(3)+"</center></h1>");
		pw.println("<h1><center>Address :"+rs.getString(4)+"</center></h1>");
		pw.println("<h1><center>Email :"+rs.getString(5)+"</center></h1>");
		pw.println("<h1><center>Password :"+rs.getString(6)+"</center></h1>");
		pw.println("<h1><center>Confirm Password :"+rs.getString(7)+"</center></h1>");
		
		}
		pw.println("<h1><center><a href='Index.html'>HomePage</a></center></h1>");
		
		
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