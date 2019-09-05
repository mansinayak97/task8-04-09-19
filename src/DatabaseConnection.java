import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	public static Connection getCon() throws Exception {
		
		 Class.forName("com.mysql.jdbc.Driver");
		 String url = "jdbc:mysql://localhost:3306/db";
		 String user = "root";
		 String password = "root";
		 Connection con =DriverManager.getConnection(url, user, password);
		 return con;
	
	}
}
