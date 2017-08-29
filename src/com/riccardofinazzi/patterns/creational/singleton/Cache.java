package com.riccardofinazzi.patterns.creational.singleton;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	private static Cache instance;

	private static Cache getCache() {
		if( instance != null) {
			return instance;
		} else {
			return instance = new Cache();
		}
	}

	private Map<String, String> map;

	private Cache() {
		map = new HashMap<String, String>();
	}

	public String get( String key) {
		return map.get(key);
	}

	public void put( String key, String value) {
		map.put(key, value);
	}

	public static void main( String[] args) {
		// TODO Auto-generated method stub
		Cache c = Cache.getCache();
		c.put("Titolo1", "descrizione1");
		c.put("Titolo2", "descrizione2");
		c.put("Titolo3", "descrizione3");
		
		System.out.println(c.get("Titolo3"));

		c.put("Titolo3", "nuova descrizione");
		
		Cache c2 = Cache.getCache();
		System.out.println(c2.get("Titolo3"));
	}

}
