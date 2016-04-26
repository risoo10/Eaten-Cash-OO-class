package models;

import java.util.*;


public class MapaObjektov {
	
	private static Map<String, Object> mapaObjektov = new LinkedHashMap<>();
	
	public static void vlozObjekt(String id, Object o){
		mapaObjektov.put(id, o);
	}
	
	public static Object vratObjekt(String id){
		return mapaObjektov.get(id);
	}

}
