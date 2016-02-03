package org.pushio.webapp.util;

import java.util.regex.Pattern;

public class StrUtil {
	
	
	public static Pattern pattern = Pattern.compile("[0-9]*"); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean isNumeric(String str){ 
	    return pattern.matcher(str).matches();    
	 } 

}
