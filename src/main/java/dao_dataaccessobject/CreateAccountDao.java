package dao_dataaccessobject;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto_datatransfer_object.CreateAccountDto;

public class CreateAccountDao {

	
	
	public static void getDao() throws SQLException, ClassNotFoundException {
		
		PrintWriter pw=null;
		CreateAccountDto dto=null;
		
		PreparedStatement ps=null;
		Connection conn=null;
		int result=0;
			
		//jdbc code
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//load driver class
		
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
		
		String query="insert into CreateAccount values(?,?,?,?,?,?,?)";
		
		ps=conn.prepareStatement(query);
		
		dto=new CreateAccountDto();
		ps.setString(1,dto.getName());
		ps.setInt(2,dto.getAge());
		ps.setString(3,dto.getGender());
		ps.setString(4,dto.getAddress());
		ps.setString(5,dto.getEmail());
		ps.setString(6,dto.getNewPassword());
		ps.setString(7,dto.getConfirmPassword());
		
		result=ps.executeUpdate();
		if(result!=0) {
			pw.println("<h1><center>Thank You "+dto.getName()+"</h1></center>");
			pw.println("<h1><center>Your Account has been Created Successfully</center></h1>");
		}
		ps.close();
		conn.close();
		pw.close();
				
		
	}
	
	
	
	

}
