package com.movierecommender.algorithms;

import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class AlgorithmEvaluation {

	
	/**
	 * This function returns the ItemSimilarity parameter by running most evaluated algorithm
	 * @param dm
	 * @return ItemSimilarity
	 */
	public ItemSimilarity getItemSimilarity(DataModel dm){
		
		ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
		
		return sim;
		
	}
	
}
