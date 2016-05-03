package controllers;

import java.util.List;

import models.*;
import views.*;
import views.formulare.*;

public class RodicController extends UserController{
	
	private static Rodic aktualRodic;
	
	// Metoda prihlasi pouzivatela podla Id v parametri.
	public static void getDomov(String userId) {
		Users allUsers = (Users)MapaObjektov.vratObjekt("users");
		aktualRodic = (Rodic) allUsers.getUser(userId);
		aktualUser = aktualRodic;
		
		UserController.getDomov();
		
		// Prida funkcionalitu Rodica.
		PanelTlacidla pt = new PanelTlacidla();
		pt.tlacidlaPreRodic();	
		
		
	}
	
	// Vyhodi panel na prihlasenie Rodica a na vytvorenie noveho uctu Rodica.
	public static void getPrihlasenieRodic() {
		
		// Nacita vsetkych existujucich rodicov
		Users allUsers = (Users)MapaObjektov.vratObjekt("users");
		List<Rodic> rodicia = allUsers.getRodicUsers();
		
		MapaNahladov.pridajNahlad("PrihlasenieRodic", new PrihlasenieRodic(rodicia));
		
	}
	
	public static void getNovyRodicDomov(){
		Users users	 = (Users)MapaObjektov.vratObjekt("users");
		
		// Nacita vstup a neskor ovaliduje
		String userId = ((PrihlasenieRodic) MapaNahladov.vratNahlad("PrihlasenieRodic")).getUserId();
		
		if(ovalidujNovehoUzivatela(userId) == null) {  // Ak validacia prebehne spravne, vrati null
			
			// Pridam noveho pouzivatela do listu vsetkych Pouzivatelov
			aktualUser = new Rodic(userId);
			users.addUser(aktualUser);
		
			// Prejdem domov
			RodicController.getDomov(aktualUser.getId());
		}
		
		
		
	}
	
	
	
}
