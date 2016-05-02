package models;

import java.util.*;

public class Master extends User {
	
	private static Master instance = null;
	private String heslo;
	
	// Informacie o vsetkych limitoch pre ostatnych.
	private List<Limit> limity = new ArrayList<Limit>();
	
	
	// Objekt master sa vytvori iba raz a preto PRIVATE konsktruktor.
	private Master(String id, String newHeslo) {
		super(id);
		heslo = newHeslo;
	}

	// Metoda vrati instanciu objektu a vytvori novu ak neexistuje.
	public static Master getInstance() {
		if ( instance == null ){
			
			// Jedina instancia s TYMITO UDAJMI ********
			instance = new Master("admin", "password10");
			
			return instance;
		} else {
			return instance;
		}
	}
	
	// Overi heslo, vracia TRUE / FALSE. Nikto tak nema pristup k heslu, okrem MASTER objektu.
	public boolean checkPassword(String heslo){
		if(this.heslo.equals(heslo))
			return true;
		else
			return false;
	}
}
