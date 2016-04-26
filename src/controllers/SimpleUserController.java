package controllers;


import java.util.*;

import javax.swing.JOptionPane;

import models.*;
import views.*;


public class SimpleUserController extends UserController{
	
	// Metoda nacita vsetkych existujucich pouzivatelov.
	public static void getSimpleUsers(){
		
		// Nacitam existujucich pouzivatelov typu SimpleUser
		Users allUsers = (Users)MapaObjektov.vratObjekt("users");
		List<SimpleUser> simpleUsers = new ArrayList<>();
		for(User user : allUsers.getUsers()){
			if(user instanceof SimpleUser)
				simpleUsers.add((SimpleUser) user);
		}
		
		// Vytvorim nahlad pre konkretne prihlasenie alebo vytvorenie noveho Obzcajneho Uzivatela.
		MapaNahladov.pridajNahlad("SimpleUserPrihlasenie", new PrihlasenieSimpleUsers(simpleUsers));
		
	}
	
	
	// Metoda prejde na domovsku stranku existujuceho pouzivatela.
	public static void getDomov(String userId){
		// Vyhladaj existujuceho pouzivatela s danym userId
		Users users = (Users) MapaObjektov.vratObjekt("users");
		aktualUser = (SimpleUser) users.getUser(userId);
		UserController.getDomov();
		
	}
	
	// Metoda vytvori noveho pouzivatela s Id zo vstupu. Potom prejde na domovsku stranku.
	public static void getNovyUserDomov(){
		// Nacita vstup a ovaliduje
		String errorMsg = "";
		String userId = ((PrihlasenieSimpleUsers) MapaNahladov.vratNahlad("SimpleUserPrihlasenie")).getUserId();
		// Ak nepreslo UserId validaciou, tak vyhodi upozornenie
		if(userId.isEmpty()) {
			errorMsg = "Pole MENO: nesmie zostat prazdne!";
			JOptionPane.showMessageDialog(null, errorMsg);
		} else {
			aktualUser = new SimpleUser(userId);
			((Users)MapaObjektov.vratObjekt("users")).addUser(aktualUser);
			UserController.getDomov();
		}
	}
	
}
