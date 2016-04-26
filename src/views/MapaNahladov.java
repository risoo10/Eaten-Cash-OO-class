package views;

import java.util.*;

public class MapaNahladov {
	private static LinkedHashMap<String, Nahlad> nahlady = new LinkedHashMap<>();
	
	// prida nahlad do mapy
	public static void pridajNahlad(String id, Nahlad nahlad){
		nahlady.put(id, nahlad);
		nahlad.setId(id);
	}
	
	// odstrani nahlad z mapy
	public void removeNahlad(String id){
		nahlady.remove(id);		
	}
	
	// vrati Nahlad z mapy
	public static Nahlad vratNahlad(String id){
		return nahlady.get(id);
	}
}
