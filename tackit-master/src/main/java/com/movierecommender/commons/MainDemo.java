/**
 * 
 */
package com.movierecommender.commons;

import com.movierecommender.itemrecommend.ItemRecommendForUser;
import com.movierecommender.userrecommend.UserRecommend;

/**
 * @author KnowledgeDiscoverers
 *
 */
public class MainDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int userAge = 11;
		String gender = "M";
		boolean flag = true;
		long userId = 289;
		
		ItemRecommendForUser iuser = new ItemRecommendForUser();
		
		System.out.println("Item2Item based recommendation on Clustered Data :");
		//iuser.runClustRecommend(userAge, gender, flag,userId);
		System.out.println("Item2Item based recommendation on Non Clustered Data :");
		//iuser.runNonClustRecommend(userAge, gender, flag,userId);
		
		
		UserRecommend uuser = new UserRecommend();
		System.out.println("User2User based recommendation on Clustered Data :");
		uuser.runClustRecommend(userAge, gender, flag,userId);
		System.out.println("User2User based recommendation on Non Clustered Data :");
		//uuser.runNonClustRecommend(userAge, gender, flag,userId);
		
		if(!flag) // In case of new user following function will be called.
			iuser.recommendationForNewUser(678);
		
	}

}
//public class MainDemo {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		int userAge = 11;
//		String gender = "M";
//		boolean flag = true;
//		long userId = 289;
//		
//		ItemRecommendForUser iuser = new ItemRecommendForUser();
//		
//		System.out.println("Item2Item based recommendation on Clustered Data :");
//		//iuser.runClustRecommend(userAge, gender, flag,userId);
//		System.out.println("Item2Item based recommendation on Non Clustered Data :");
//		//iuser.runNonClustRecommend(userAge, gender, flag,userId);
//		
//		
//		UserRecommend uuser = new UserRecommend();
//		System.out.println("User2User based recommendation on Clustered Data :");
//		uuser.runClustRecommend(userAge, gender, flag,userId);
//		System.out.println("User2User based recommendation on Non Clustered Data :");
//		//uuser.runNonClustRecommend(userAge, gender, flag,userId);
//		
//		
//	}
//
//}
