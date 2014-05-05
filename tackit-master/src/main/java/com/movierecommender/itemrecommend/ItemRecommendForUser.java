package com.movierecommender.itemrecommend;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import com.movierecommender.commons.EvaluateUser;
import com.movierecommender.commons.FetchDataModel;
import com.movierecommender.commons.Statics;
import com.movierecommender.algorithms.AlgorithmEvaluation;

public class ItemRecommendForUser {
	
	public List<RecommendedItem> getRecommendation(long userId, DataModel dm){
		ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
		GenericItemBasedRecommender recommender;
		List<RecommendedItem> recommendations = null;
		try{
			sim = new LogLikelihoodSimilarity(dm);
			recommender = new GenericItemBasedRecommender(dm, sim);
			recommendations = recommender.recommend(userId, Statics.DISPLAY_ITEMS);
		} catch (TasteException e) {
			System.out.println("There is a Taste Exception");
			e.printStackTrace();
		}
		return recommendations;
	}


	public List<RecommendedItem> recommendationForNewUser(int itemId)
	{
		DataModel dm;	
		List<RecommendedItem> recommendations = null;
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getNonClusteredDataModel();
		//rec.getRecommendation(userId,dm);
		ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
		try {
			recommendations = recommender.mostSimilarItems(itemId, Statics.DISPLAY_ITEMS);
//			for(RecommendedItem recommendation : recommendations){
//				System.out.println("Item Id : "+recommendation.getItemID()+" :: "+recommendation.getValue());
//			}
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recommendations;
	}
	
	
	public List<RecommendedItem> runNonClustRecommend(int userAge, String gender, boolean flag, long userId){
		DataModel dm;		
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getNonClusteredDataModel();
		List<RecommendedItem> items = getRecommendation(userId,dm);
		return items;
	}
	
	public List<RecommendedItem> runClustRecommend(int userAge, String gender, boolean flag, long userId){
		DataModel dm;		
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getClusteredDataModel(flag, userAge,gender);
		//getRecommendation(userId,dm);
		List<RecommendedItem> items = getRecommendation(userId,dm);
		return items;
	}
	
/*	public static void main(String[] args) {
		boolean flag = true;		
		//flag indicates whether it is a new user or the old one

		int userAge = 18;
		String gender = "M";
		DataModel dm;		
		ItemRecommendForUser rec = new ItemRecommendForUser();
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getNonClusteredDataModel();
		rec.getRecommendation(57,dm);
	}*/
}


