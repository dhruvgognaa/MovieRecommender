package com.movierecommender.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.movierecommender.commons.Statics;
import com.movierecommender.domain.User;

public class UpdateTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConn conn = new DBConn();
		//conn.assignUserName();
		//conn.assignPassword();
		User user = new User("username", "password", 15, "occupation", "M", "95112", 1);
		String userid = "12345";
		String toAppend = userid + "|"
				+ user.getAge() + "|"
				+ user.getGender() + "|"
				+ user.getOccupation() + "|"
				+ user.getZipcode() + "\n";
		System.out.println("To append: " + toAppend);
		try{
			String csvFile = Statics.CSV_PATH + "data\\u.user";
			File file = new File(csvFile);
			System.out.println("file path: " + csvFile);
			System.out.println("to append: " + toAppend);
			System.out.println("file: " + file.getAbsolutePath());
			if(file.exists())
				System.out.println("file exists");
			FileWriter fileWritter = new FileWriter(file, true);
		    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		    bufferWritter.write(toAppend);
		    bufferWritter.flush();
		    bufferWritter.close();
		} catch (IOException e) {
			System.out.println("Couold not update user in file");
			e.printStackTrace();
		}
	}

}
