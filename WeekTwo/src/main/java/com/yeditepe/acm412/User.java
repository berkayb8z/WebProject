package com.yeditepe.acm412;

public class User {
	
	private int id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String password;
	
	
	
	
	
	public User(int id, String name, String surname, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}



	
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getSurname() {
		return surname;
	}




	public void setSurname(String surname) {
		this.surname = surname;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public String toString() {
		String ans = "User Info\n";
		ans += "Name:" + this.getName();
		ans += "Surname:" + this.surname;
		ans += "Email:" + this.email;
		return ans;
	}
	
	
	
	

	

}
