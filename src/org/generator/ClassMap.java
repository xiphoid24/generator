package org.generator;

import java.util.HashMap;
import java.util.Map;

public class ClassMap {

	private Map<String, Class> classMap;

	public ClassMap() {
		this.classMap = new HashMap<String, Class>();
		classMap.put("Boolean", Boolean.class);
		classMap.put("Byte", Byte.class);
		classMap.put("Short", Short.class);
		classMap.put("Character", Character.class);
		classMap.put("Integer", Integer.class);
		classMap.put("Long", Long.class);
		classMap.put("Float", Float.class);
		classMap.put("Double", Double.class);
		classMap.put("String", String.class);
		classMap.put("BigInteger", java.math.BigInteger.class);
		classMap.put("BigDecimal", java.math.BigDecimal.class);
		classMap.put("Date", java.sql.Date.class);
		classMap.put("Time", java.sql.Time.class);
		classMap.put("Timestamp", java.sql.Timestamp.class);
		classMap.put("Collection", java.util.Collection.class);
		classMap.put("List", java.util.List.class);
		classMap.put("Map", java.util.Map.class);
		classMap.put("Set", java.util.Set.class);
	}

	public Class get(String key) {
		if (!classMap.containsKey(key)) {
			return void.class;
		}
		return classMap.get(key);
	}
	
	public void put(String name, Class newClass) {
		classMap.put(name, newClass);
	}
	
	public void stringify() {
		for (Map.Entry<String, Class> next : classMap.entrySet()) {
			System.out.println(next.getKey()+"  >>  "+next.getValue().toString());
		}
	}
	

}
