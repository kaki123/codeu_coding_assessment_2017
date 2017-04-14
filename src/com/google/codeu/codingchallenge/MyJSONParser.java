// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MyJSONParser implements JSONParser {

	
 public static void main(String args[]) throws IOException {
	 MyJSONParser p = new MyJSONParser();
	 //p.parse("{\"first\":\"sam\"}");
	 p.parse("{ \"name\":{\"first\":\"sam\", \"last\":\"doe\" } }");
	
	 
	 //{"key":"value"} - step 1 
	 //{"key":"value" , "key1":"value1"}  - step 2
	 //"key": { "key": "value"}  - step 3 
	 // { }
	 // { "name": "sam doe" }
	 // { "name": { "first": "sam", "last": "doe" } }
 }
  @Override
  public JSON parse(String in) throws IOException {
	  MyJSON obj = new MyJSON();
	  MyJSON obj2 = new MyJSON();
	  // code ... while loop 
	  Scanner s = new Scanner(in); //"{ \"name\":\"sam doe\" }"
	  Pattern one = Pattern.compile("\\{\\s*\"([a-zA-Z\\s*]+)\"\\s*:\\s*\"([a-zA-Z\\s*]+)\"\\s*\\}");
	  Pattern two = Pattern.compile("\\{\\s*\"([a-zA-Z\\s*]+)\"\\s*:\\s*\\{\\s*\"([a-zA-Z\\s*]+)\"\\s*:\\s*\"([a-zA-Z\\s*]+)\"\\s*,*\\s*\"([a-zA-Z\\s*]+)\"\\s*:\\s*\"([a-zA-Z\\s*]+)\"\\s*\\}\\s*\\}");
	  								// ("{ \"name\":{\"first\":\"sam\", \"last\":\"doe\" } }")
	  //Pattern pa = Pattern.compile("\\{\"([a-zA-Z\\s*]+)\"\\s*:\\s*\\{\"([a-zA-Z//s*]+)\"\\s*:\\s*\"([a-zA-Z\\s*]+)\"\\s*,\\s*\"([a-zA-Z\\s*]+)\"\\s*:\\s*\"([a-zA-Z\\s*]+)\"\\}\\}");
	  Matcher m1 = one.matcher(in);
	  Matcher m2 = two.matcher(in);
	  boolean o = m1.matches();
	  boolean t = m2.matches();
	  
	  
	  if(o==true){	  	
		   MatchResult result = m1;  	
		   for (int i=1; i<=result.groupCount(); i=i+2){
			   obj.setString(result.group(i),result.group(i+1));
		  }	 
	   }
	  if(t==true){
	    	MatchResult result2 = m2;
	    	JSON obj3 = new MyJSON();
	        for (int i=2; i<=result2.groupCount(); i=i+2){        	
	        	obj3.setString(result2.group(i),result2.group(i+1));            
	        }
	        obj2.setObject(result2.group(1), obj3 );
	   }
    return obj;
  }
}


