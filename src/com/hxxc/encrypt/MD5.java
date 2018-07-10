package com.hxxc.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MD5 md5 = new MD5();
	}
	
	public static String bytetoString(byte[] digest) {  

	    String str = "";  
	    String tempStr = "";  
	    for (int i = 1; i < digest.length; i++) {   
	     tempStr = (Integer.toHexString(digest[i] & 0xff));   
	     if (tempStr.length() == 1) {    
	      str = str + "0" + tempStr;   
	     }
	     else {    
	      str = str + tempStr;   
	     }  
	    }  
	    return str.toLowerCase();

	}
	
	public static String encrypt(String data){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		md.update(data.getBytes()); 
		byte b[] = md.digest(); 
		int i; 

		StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
			buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
		}
			
		return buf.toString();
	}
}
