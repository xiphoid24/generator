package org.generator;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String,String> fields = new HashMap<String,String>();
		fields.put("code", "String");
		fields.put("desc", "String");
		fields.put("count", "Integer");
		fields.put("price", "Float");
		fields.put("date", "Date");
		fields.put("test", "List");
		
		Map<String,String> fields2 = new HashMap<String,String>();
		fields2.put("code", "String");
		fields2.put("desc", "String");
		fields2.put("item", "Item");

		
		
		Generator gen = new Generator("com.coolprogrammers");
		//gen.printClassMap();
		//gen.initClass("Item");
		gen.domainClass("Item", fields);
		gen.build();
		gen.domainClass("test", fields2);
		gen.build();
		//System.out.println("\n\n----------------\n\n");
		//gen.printClassMap();
		
	}

}
