package dao_dataaccessobject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto_datatransfer_object.SearchSpecilistDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voclass.SearchSpecilistVo;

public class SearchSpecilist extends HttpServlet  {
	public String roomnumber=null;
	public SearchSpecilistVo vo=null;
	public SearchSpecilistDto dto=null;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw=null;
		
		String r=null;
		pw=resp.getWriter();
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		resp.setContentType("text/html");
		//for request
		roomnumber=req.getParameter("roomnumber");
		
		//jdbc code
		//load driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			//load driver class
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			r="Select * from specilist where roomnumber=?";
			
			//load preparedStatement
			 vo=new SearchSpecilistVo();
			vo.setRoomnumber(roomnumber);
			dto=new SearchSpecilistDto();
			dto.setRoomnumber(Integer.parseInt(vo.getRoomnumber()));
			
			
			
			ps=connection.prepareStatement(r);
			ps.setInt(1, dto.getRoomnumber());
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				flag=true;
				
				pw.println("<h1><center> Doctor Details :</center></h1>");
				
				pw.println("<h1><center> Doctor Name :"+rs.getString(1)+"</center></h1>");
				pw.println("<h1><center> Department Name :"+rs.getString(2)+"</center></h1>");
				pw.println("<h1><center> Specilist :"+rs.getString(3)+"</center></h1>");
				pw.println("<h1><center> Floor Number :"+rs.getInt(4)+"</center></h1>");
				pw.println("<h1><center> Room Number :"+rs.getInt(5)+"</center></h1>");
				pw.println("<h1><center> Contact Number :"+rs.getLong(6)+"</center></h1>");
				pw.println("<h1><center> Email :"+rs.getString(7)+"</center></h1>");
			
				pw.println("<footer><h1><center><a href='SearchSpecilist.html'>Back to Page</center></a></h1></footer>");
			}
			else if(flag==false){
				pw.println("<h1> Not fetch date for "+roomnumber+"</h1>");
				
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
			pw.close();
		}
		
		
		
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doGet(req,resp);
		
	}
		
			
	
}
