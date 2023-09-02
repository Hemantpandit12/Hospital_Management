package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import dto_datatransfer_object.BookAppointmentDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.BookAppointmentVo;
			
public class BookAppointmentController extends HttpServlet {
	 BookAppointmentVo vo;
	BookAppointmentDto dto;
	String name;
	String age;
	String adharNumber;
	String diseaseName;
	String address;
	PrintWriter pw;
	Connection connection;
	PreparedStatement ps;
	int result;
	String insert;	
	
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		//for download page
	//	response.setHeader("Content-Disposition", "attachment; filename=\"yourfile..pdf\"");

	//	OutputStream outputStream = response.getOutputStream();
		
		
		
		pw=response.getWriter();
		
		//for request
	name=request.getParameter("name");
	age=request.getParameter("age");
	adharNumber=request.getParameter("adharNumber");
	diseaseName=request.getParameter("diseaseName");		
	address=request.getParameter("address");
		
		//vo class
		
		vo=new BookAppointmentVo();
		vo.setName(name);
		vo.setAge(age);
		vo.setAdharNumber(adharNumber);
		vo.setDiseaseName(diseaseName);
		vo.setAddress(address);
		
		//dto class
		dto=new BookAppointmentDto();
		dto.setName(vo.getName());
		dto.setAge(Integer.parseInt(vo.getAge()));
		dto.setAdharNumber(Long.parseLong(vo.getAdharNumber()));
		dto.setDiseaseName(vo.getDiseaseName());
		dto.setAddress(vo.getAddress());
		
		pw.println("<header> <center><h1>Hospital Application </h1></center></header>");
		
		pw.println("<h2><center>Name :"+dto.getName()+"</center></h2>");
		pw.println("<h2><center>Age :"+dto.getAge()+"</center></h2>");
		pw.println("<h2><center>Adhar Number :"+dto.getAdharNumber()+"</center></h2>");
		pw.println("<h2><center>Disease Name :"+dto.getDiseaseName()+"</center></h2>");
		pw.println("<h2><center>Address :"+dto.getAddress()+"</center></h2>");
		
	
		
		//Jdbc connectivity
		//load driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			//load driver class
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			insert="insert into appointment values(?,?,?,?,?)";
			
			ps=connection.prepareStatement(insert);
			
			ps.setString(1,dto.getName());
			ps.setInt(2,dto.getAge());
			ps.setLong(3,dto.getAdharNumber());
			ps.setString(4,dto.getDiseaseName());
			ps.setString(5,dto.getAddress());
			
			result=ps.executeUpdate();
			
			if(result!=0) {
				pw.println("<h1><center>Thank You "+dto.getName()+"</center></h1>");
				pw.println("<h1><center>Your Appointment have been successfully Updated</center></h1>");
			}
			
			
			
			//outputStream.flush();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		pw.println("<h1><a href='BookAppointment.html'>BackPage</a></h1>");
		
		
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
		//outputStream.close();

		pw.close();
		
		
		
		
	}	
	
	
	
	
}
