package com.nit.saif.utility;

import org.springframework.stereotype.Component;

@Component
public class RandomPasswordGenerator {
	
    public static String getAlphaNumericString(){
        String characterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(8);  
        for (int i = 0; i < 8; i++) {
            int index
                = (int)(characterString.length()* Math.random());  
            sb.append(characterString.charAt(index));
        }  
        return sb.toString();
    }
  

}
