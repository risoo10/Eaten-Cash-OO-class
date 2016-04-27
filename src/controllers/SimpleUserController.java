package controllers;


import java.util.*;

import javax.swing.JOptionPane;

import models.*;
import views.*;
import views.formulare.PrihlasenieSimpleUsers;


public class SimpleUserController extends UserController{
	
	private static SimpleUser aktSimpleUser;
	
	
	// Metoda nacita vsetkych existujucich pouzivatelov.
	public static void getPrihlasenieSimpleUsers(){
		
		// Nacitam existujucich pouzivatelov typu SimpleUser
		Users allUsers = (Users)MapaObjektov.vratObjekt("users");
		List<SimpleUser> simpleUsers = allUsers.getSimpleUsers();
		
		
		// Vytvorim nahlad pre konkretne prihlasenie alebo vytvorenie noveho Obzcajneho Uzivatela.
		MapaNahladov.pridajNahlad("SimpleUserPrihlasenie", new PrihlasenieSimpleUsers(simpleUsers));
		
	}
	
	
	// Metoda prejde na domovsku stranku existujuceho pouzivatela.
	public static void getDomov(String userId){
		// Vyhladaj existujuceho pouzivatela s danym userId
		Users users = (Users) MapaObjektov.vratObjekt("users");
		aktSimpleUser = (SimpleUser) users.getUser(userId);
		aktualUser = aktSimpleUser;
		
		UserController.getDomov();
		
		
		// Zsisti ci existuju nejake Upozornenia pre Aktualne prihlaseneho Pouzivatela.
		if(aktSimpleUser.checkUpozornenia()){
			// Zistim ci neexistuju Upzornenia pre pouzivatela.
			String upozornenie = null;
			Okno okno = nacitajOkno();
			okno.nastavPovodnuVelkost(600); // Pri pridavani upozornenia sa zvacsi velkost okna
			upozornenie = aktSimpleUser.getUpozornenie();
			MapaNahladov.pridajNahlad("Upozornenie", new UpozorneniePanel(aktSimpleUser.getUpozornenie(), aktSimpleUser.getRodic().getId()));
			okno.obnovZmeny();
		}
		
		// Zisti ci existuju nejake Limity pre Aktualne prihlaseneho Pouzivatela.
		
		
		
	}
	
	// Metoda vytvori noveho pouzivatela s Id zo vstupu. Potom prejde na domovsku stranku.
	public static void getNovyUserDomov(){
		
		Users users = (Users)MapaObjektov.vratObjekt("users");
		
		// Nacita vstup a neskor ovaliduje
		String userId = ((PrihlasenieSimpleUsers) MapaNahladov.vratNahlad("SimpleUserPrihlasenie")).getUserId();
		
		if(ovalidujNovehoUzivatela(userId) == null) {  // Ak validacia prebehne spravne, vrati null
			
			// Pridam noveho pouzivatela do listu vsetkych Pouzivatelov
			aktualUser = new SimpleUser(userId);
			users.addUser(aktualUser);
			
			// Prejdem domov
			SimpleUserController.getDomov(aktualUser.getId());
		}
	}
	
}
