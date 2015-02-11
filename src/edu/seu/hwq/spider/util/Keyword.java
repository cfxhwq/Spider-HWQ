package edu.seu.hwq.spider.util;

public class Keyword {


	public static String getKeywords(String text){
		String result = null;
		
		result = getKeywordsbyJieba(text);
		
		return result;
	}
	
	public native static String getKeywordsbyJieba(String text);

}
