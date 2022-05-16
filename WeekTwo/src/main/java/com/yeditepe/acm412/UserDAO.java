package com.yeditepe.acm412;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class UserDAO {
	
	private Connection dbconnection;
	
	
	public UserDAO() {
		dbconnection = Database.getConnection();
	}
	
	
	public ArrayList<User> getAllUsers() {
		User user;
		// user'lar� bar�nd�racak bir arrayList yarat�yoruz
		ArrayList<User> users = new ArrayList<User>();
		String query = "Select * from users";
		try {
		// query'nin �al��mas� i�in bir statement'a ihtiyac�m�z var
		Statement st = dbconnection.createStatement();
		// Statement nesnesi �zerinden query'yi �al��t�r�yoruz
		ResultSet rs = st.executeQuery(query);	
		while(rs.next()) {
			int id = rs.getInt("uid");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String password = rs.getString("password");
			user = new User(id,name,surname,email,phone,password);
			users.add(user);
		}	
		}
		catch(SQLException e) {
			System.out.println("Query cannot be run");
		}
		return users;		
	}
	
	
	
	public User checkUser(String eposta, String sifre) {
		User user = null;
		String query = "Select * from users where email=? and password=?";
		try {
			PreparedStatement st = dbconnection.prepareStatement(query);
			// 1 burada 1.soru i�areti yerine gelecek �ey, 2de 2.soru i�areti yerine gelecek �ey
			st.setString(1, eposta);
			st.setString(2, sifre);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("uid");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String password = rs.getString("password");
				user = new User(id,name,surname,email,phone,password);
			}			
		}
		catch(SQLException e) {
			System.out.println("Baglanti hatasi");
		}
		return user;
		
		
	}
	

	
	public int addUser(User u) {
		String query = "INSERT INTO users(name,surname,email,phone,password)" + " VALUES (?,?,?,?,?)";
		int cevap = 0;
		try {
			PreparedStatement st = dbconnection.prepareStatement(query);
			st.setString(1, u.getName());
			st.setString(2, u.getSurname());
			st.setString(3, u.getEmail());
			st.setString(4, u.getPhone());
			st.setString(5, u.getPassword());
			// INSERT,UPDATE,DELETE i�lemleri i�in executeUpdate() komutunu �al��t�r
			cevap = st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Yeni kayit olusturulamadi....");
		}
		return cevap;		
	}
	
	
	

	
	

}
