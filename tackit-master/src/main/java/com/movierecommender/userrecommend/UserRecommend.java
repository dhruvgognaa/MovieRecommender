package com.movierecommender.userrecommend;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.movierecommender.commons.FetchDataModel;
import com.movierecommender.commons.Statics;
import com.movierecommender.itemrecommend.ItemRecommendForUser;

public class UserRecommend {

	public List<RecommendedItem> getRecommendation(long userId, DataModel dm){
		UserSimilarity sim;
		UserNeighborhood n;
		Recommender recommender;
		List<RecommendedItem> recommendations = null;
		try{
			sim = new PearsonCorrelationSimilarity(dm);
			sim = new LogLikelihoodSimilarity(dm);
			n  =new NearestNUserNeighborhood(5, sim, dm);
			recommender = new GenericUserBasedRecommender(dm,n, sim);
			recommendations = recommender.recommend(userId, Statics.DISPLAY_ITEMS);
			/*
			UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
			//UserSimilarity sim = new EuclideanDistanceSimilarity(dm,Weighting.WEIGHTED);
			//UserSimilarity sim = new SpearmanCorrelationSimilarity(dm);
			
			UserNeighborhood n  =new NearestNUserNeighborhood(5, sim, dm);
			
			//UserNeighborhood n  =new ThresholdUserNeighborhood(5, sim, dm);
			
			Recommender recommender = new GenericUserBasedRecommender(dm,n, sim);
			//GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, tmotoSimilarity);
			List<RecommendedItem> recommendations = recommender.recommend(userId, 5);

			for(RecommendedItem recommendation : recommendations){
				System.out.println("Item Id : "+recommendation.getItemID()+" :: "+recommendation.getValue());
			}
			*/
		} catch (TasteException e) {
			System.out.println("There is a Taste Exception");
			e.printStackTrace();
		}
		return recommendations;
	}

	public List<RecommendedItem> runNonClustRecommend(int userAge, String gender, boolean flag, long userId){
		DataModel dm;		
		UserRecommend urec = new UserRecommend();
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getNonClusteredDataModel();
		//getRecommendation(userId,dm);
		List<RecommendedItem> items = getRecommendation(userId,dm);
		return items;
	}
	
	public List<RecommendedItem> runClustRecommend(int userAge, String gender, boolean flag, long userId){
		DataModel dm;		
		UserRecommend urec = new UserRecommend();
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getClusteredDataModel(flag, userAge,gender);
		//getRecommendation(userId,dm);
		List<RecommendedItem> items = getRecommendation(userId,dm);
		return items;
		
	}
	
	
	
	public static void main(String[] args) {
		boolean flag = true;		
		//flag indicates whether it is a new user or the old one

		int userAge = 18;
		String gender = "M";
		DataModel dm;		
		UserRecommend urec = new UserRecommend();
		FetchDataModel fdm = new FetchDataModel();
		dm = fdm.getNonClusteredDataModel();
		urec.getRecommendation(110,dm);
	}
}
