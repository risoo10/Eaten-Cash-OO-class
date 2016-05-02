package controllers;

import views.*;

import java.io.File;
import java.util.List;

import models.*;

public class PrihlasenieController {

	public static void main(String[] args) {
		
		
		MapaNahladov.pridajNahlad("Okno", new Okno());
		
		// vytvor Router
		new Router();
		
				
		// Zisti ci existuje subor s datami.
		File f = new File("VsetkyData.ser");
		if(f.exists()){
			MapaObjektov.vlozObjekt("users", UsersController.nacitajVsetkyData());
		} else {
			// vytvor Pouzivatelov
			MapaObjektov.vlozObjekt("users", new Users());
		}
		
		// naciatj typy pouzivatelov a vytvor prihlasenie
		String[] usertyp = Users.userTyp;
		MapaNahladov.pridajNahlad("Prihlasenie", new Prihlasenie(usertyp));
	}
	

}
