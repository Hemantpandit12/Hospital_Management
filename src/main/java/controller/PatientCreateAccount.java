package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao_dataaccessobject.CreateAccountDao;
import dto_datatransfer_object.CreateAccountDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.CreateAccountVo;

public class PatientCreateAccount extends HttpServlet{

	String name;
	String age;
	String gender;
	String address;
	String email;
	String newPassword;
	String confirmPassword;
	String id;
	
	static CreateAccountDao dao;
	static CreateAccountVo vo;
	static CreateAccountDto dto;
	PrintWriter pw;
	
	PreparedStatement ps;
	Connection conn;
	int result;
	
	
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter pw=null;
		
		
		
		
		
		response.setContentType("text/html");
		
		pw=response.getWriter();
		
		//for request
		name=request.getParameter("name");
		age=request.getParameter("age");
		gender=request.getParameter("gender");
		address=request.getParameter("address");
		email=request.getParameter("email");
		newPassword=request.getParameter("newPassword");
		confirmPassword=request.getParameter("confirmPassword");
		id=request.getParameter("id");
		//create vo object 
		
		vo=new CreateAccountVo();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setNewPassword(newPassword);
		vo.setConfirmPassword(confirmPassword);
		vo.setId(id);
		
		//create dto object
		
		dto=new CreateAccountDto();
		dto.setName(vo.getName());
		dto.setAge(Integer.parseInt(vo.getAge()));
		dto.setGender(vo.getGender());
		dto.setAddress(vo.getAddress());
		dto.setEmail(vo.getAddress());
		dto.setNewPassword(vo.getNewPassword());
		dto.setConfirmPassword(vo.getConfirmPassword());
		dto.setId(Integer.parseInt(vo.getId()));
		
		
		
		//jdbc code		
		//load driver class
		
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
				
				
				String query="insert into CreateAccount values(?,?,?,?,?,?,?,?)";
				
				ps=conn.prepareStatement(query);

			

				
				ps.setString(1,dto.getName());
				ps.setInt(2,dto.getAge());
				ps.setString(3,dto.getGender());
				ps.setString(4,dto.getAddress());
				ps.setString(5,dto.getEmail());
				ps.setString(6,dto.getNewPassword());
				ps.setString(7,dto.getConfirmPassword());
				ps.setInt(8, dto.getId());
				
				
				result=ps.executeUpdate();

				
				if(result!=0) {
					dto=new CreateAccountDto();
					
					pw.println("<h1><center>Thank You "+ name+"</h1></center>");
					
					pw.println("<h1><center>Your Account has been Created Successfully</center></h1>");
				}
				
				pw.println("<h1><a href='CreateAccount.html'>Back</a></h1>");
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
		
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		
			
		
	}
		
}		
	
		
			
			
		
		

		
		
	


	
	

