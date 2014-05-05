package com.movierecommender.commons;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

public class FetchDataModel {

	public DataModel getClusteredDataModel(boolean flag, int userAge, String gender) {
		// TODO Auto-generated method stub
		DataModel dm = null;
		String fileName;
		try {

			fileName = EvaluateUser.EvaluateAgeRange.getOutputFile(gender,userAge);			
			dm = new FileDataModel( new File(fileName));
		} catch (IOException e) {
			System.out.println("There is an error.");
			e.printStackTrace();
		}
		return dm;
	}

	public DataModel getNonClusteredDataModel() {
		// TODO Auto-generated method stub
		DataModel dm = null;
		try {			
			dm = new FileDataModel(new File(Statics.CSV_PATH + "data\\movies.csv"));
		} catch (IOException e) {
			System.out.println("There is an error.");
			e.printStackTrace();
		}
		return dm;
	}
}
