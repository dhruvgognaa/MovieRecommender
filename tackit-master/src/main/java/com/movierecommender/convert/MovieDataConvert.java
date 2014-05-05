package com.movierecommender.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieDataConvert {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("data/u.user"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/userList.csv"));
		String line;
		while((line = br.readLine())!= null){
			//String []values = line.split("\\t",-1);
			String []values = line.split("|");
			bw.write(values[0]+","+values[1]+","+values[2]+","+values[3]+","+values[4]+"\n");
		}
		br.close();
		bw.close();
	}

}
