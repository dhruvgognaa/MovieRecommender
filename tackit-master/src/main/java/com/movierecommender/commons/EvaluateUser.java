package com.movierecommender.commons;

public class EvaluateUser {
	
	public static enum EvaluateAgeRange{
		BETWEEN_00_12(00,12),
		BETWEEN_13_19(13,19),
		BETWEEN_20_29(20,29),
		BETWEEN_30_45(30,45),
		BETWEEN_46_55(46,55),
		BETWEEN_56_75(56,75);

		private int max_age;
		private int min_age;


		private EvaluateAgeRange(int min, int max)
		{
			max_age=max;
			min_age=min;

		}

		public static EvaluateAgeRange getAgeRange(int age)
		{
			for(EvaluateAgeRange agerange : EvaluateAgeRange.values())		
			{
				if(age>=agerange.min_age && age<=agerange.max_age)
				{
					return agerange;
				}
			}
			return null;
		}


		public int getMinAge()
		{
			return min_age;
		}


		public int getMaxAge()
		{
			return max_age;

		}

		public static String getOutputFile(String gender, int age)
		{
			String fileName;
			EvaluateAgeRange agerange = EvaluateAgeRange.getAgeRange(age);
			if(agerange.min_age>9)
				fileName = Statics.CSV_PATH + "data\\"+"cluster"+gender.toUpperCase()+agerange.min_age+agerange.max_age+".csv";
			else
				fileName = Statics.CSV_PATH + "data\\"+"cluster"+gender.toUpperCase()+"00"+agerange.max_age+".csv";
			return fileName;
		}

	}


}
