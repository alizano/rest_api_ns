package com.alizano.utils;

import com.google.gson.Gson;

public class Utils {

	 public static boolean isJSONValid(String json) {
	      try {
	    	  Gson gson = new Gson();
	          gson.fromJson(json, Object.class);
	          return true;
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	          return false;
	      }
	  }
}
