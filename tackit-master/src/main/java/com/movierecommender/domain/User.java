package com.movierecommender.domain;

public class User {

	private int id;
	private int age;
	private String username;
	private String password;
	private String occupation;
	private String gender;
	private String zipcode;
	private int newuser;

	public User(String username, String password, int age,
			String occupation, String gender, String zipcode, int newuser) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.occupation = occupation;
		this.gender = gender;
		this.zipcode = zipcode;
		this.newuser = newuser;
	}
	
	public void setUser(User user){
		this.username = username;
		this.password = password;
		this.id = user.id;
		this.age = age;
		this.occupation = occupation;
		this.gender = gender;
		this.zipcode = zipcode;
		this.newuser = newuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public int getNewuser() {
		return newuser;
	}

	public void setNewuser(int newuser) {
		this.newuser = newuser;
	}

}
