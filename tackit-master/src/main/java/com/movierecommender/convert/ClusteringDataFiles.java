package com.movierecommender.convert;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ClusteringDataFiles {
	
	
	
	public static void uDataFile(String inf,String opf) throws IOException{
		// TODO Auto-generated method stub\ 
		BufferedReader reader = null;
		PrintWriter writer = null;
		String inputFile="data/u.data";
		String outputFile="data/u.csv";

		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile,true)));
			int i=0;
			String line = null;
			String lineSplit[]=new String[4];
			while ((line = reader.readLine()) != null) {
				line=line.replaceAll("\\s",",");
				lineSplit=line.split(",");
				writer.write(lineSplit[0]);
				writer.write(",");
				writer.write(lineSplit[1]);
				writer.write(",");
				writer.write(lineSplit[2]);
				writer.write("\n");
			}
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				reader.close();
				writer.close();
			}
		}
	
	
	public static void ageGenderClassification(String inf,String opf) throws IOException{
		// TODO Auto-generated method stub\ 
		BufferedReader reader = null, fullFileReader=null;
		String inputFile="data/u.user";
		String outputFile="data/";

		try {
			reader = new BufferedReader(new FileReader(inputFile));

			//outputFile
			String line = null;
			String lineSplit[]=new String[5];
			while ((line = reader.readLine()) != null) {
				userCount++;
				line=line.replaceAll("\\|",",");
				lineSplit=line.split(",");
				String ageRange="7699";
				int age=Integer.parseInt(lineSplit[1]);
				if (0 <= age && age <= 12)
					ageRange="0012";
				else if (13 <= age && age <= 19)
					ageRange="1319";
				else if (20 <= age && age <= 29)
					ageRange="2029";
				else if (30 <= age && age <= 45)
					ageRange="3045";
				else if (46 <= age && age <= 55)	
					ageRange="4655";
				else if (56 <= age && age <= 75)
					ageRange="5675";
				else
					ageRange="7699";
				String fileName=outputFile+"cluster"+lineSplit[2]+ageRange+".csv";
				String data=lineSplit[0];
				System.out.println(fileName);
				System.out.println(data);
				findNAppend(fileName,data);
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}	
	}

	public static void findNAppend(String fileName, String data) throws IOException{
		PrintWriter writer = null;
		String fullFile="data/u.csv";
		BufferedReader br= new BufferedReader(new FileReader(fullFile));
		String str=br.readLine();
		while (str!=null){
			if (Integer.parseInt(str.split(",")[0])==Integer.parseInt(data)){
				writer=new PrintWriter(new BufferedWriter(new FileWriter(fileName,true)));
				writer.println(str);
				writer.flush();
			}
			str=br.readLine();
		}
	}
	
	private static long userCount=0;

	public static void main(String[] args) {
		try {
			Long l=System.currentTimeMillis();
			uDataFile("a","b");
			ageGenderClassification("abc","abc");
			System.out.println("Time spent -> "+(System.currentTimeMillis()-l)/1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	

}
