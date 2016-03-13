package main;

import java.util.regex.Pattern;

public class DAO {

	public static void main(String[] args){
		String test ="/4169/1285407564500_Saffolaheart_728x90_Sanjeevkapoor.com.swf";
		System.out.println(test);
		Pattern patt = Pattern.compile("\\.[A-Z,a-z]+$");
		System.out.println(patt.matcher(test).replaceAll(".png"));
	}
}
