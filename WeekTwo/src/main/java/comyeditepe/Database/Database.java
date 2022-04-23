package comyeditepe.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Connection connection=null;
	private static final String user="root";
	private static final String password="";
	private static final String URL="jdbc:mysql//"+"localhost:3306/"+"careercampus";
	
	private Database() {
		
	}
	
	public static  Connection getConnection()  {
		if(connection==null) {
		try {	
		Class.forName("com.mysql.cj.jdbc");
		connection=DriverManager.getConnection(URL,user, password);
		}
		catch(SQLException |  ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		
		return connection;
		
	}
	

}
