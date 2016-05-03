package controllers;

import java.awt.Color;

import javax.swing.JOptionPane;

import models.Master;
import views.MapaNahladov;
import views.Okno;
import views.formulare.PrihlasenieMaster;

public class MasterController extends UserController {
	
	
	// Metoda - prihlasenie master. Vytvori ak neexistuje a nacita ak existuje.
	public static void prihlasenieInstancia(){
		aktualUser = Master.getInstance();		
		MapaNahladov.pridajNahlad("prihlasenieMaster", new PrihlasenieMaster());
	}
	
	public static void getDomov() {
		

		Okno okno = (Okno) MapaNahladov.vratNahlad("Okno");

		UserController.getDomov();
		
		// Nastav hlavnu farbu - na ZLTU
		okno.pozadieHlavicky(new Color(255, 212, 96));
		
		// TODO: funckionalita Mastera
		
		
		
	}
	
	public static void prihlasenieDomov(){
		
		Master master = Master.getInstance();
		
		// Validacia zadanych udajov.
		String username = null;
		String heslo = null;
		PrihlasenieMaster pm = (PrihlasenieMaster) MapaNahladov.vratNahlad("prihlasenieMaster");
		
		// Validacia - zisti ci su vsetky policka vyplnene.
		String errorMsg = "";
		// Prihlasovacie meno
		if(pm.getUsername().getText().isEmpty()){
			errorMsg += "Zadaj prihlasovacie meno !\n";
		} else {
			username = pm.getUsername().getText();
		}
		// Prihlasovacie heslo.
		if(pm.getPassword().getText().isEmpty()){
			errorMsg += "Musis zadat heslo !";
		} else {
			heslo = pm.getPassword().getText();
		}
		
		if(errorMsg != ""){
			JOptionPane.showMessageDialog(null, errorMsg);
		} else {
			// Validacia - zisti boli zadane spravne udaje.
			
			// Zisti ci je spravne prihlasovacie meno. Pokial nie je nepusti dalej.
			if(!username.equals(master.getId())){
				errorMsg = "Zadali ste nespravne prihlasovacie meno.";
				JOptionPane.showMessageDialog(null, errorMsg);
			} else {
				
				// Zisti ci je zadane heslo spravne
				if(master.checkPassword(heslo)){
					
					
					// Nacita domvsku stranku po spravnom zadani hesla.********
					MasterController.getDomov();
					
					
				} else {
					
					// Naopak pri zlom hesle zase vypise chybnu spravu.
					errorMsg = "Zadali ste nespravne heslo.";
					JOptionPane.showMessageDialog(null, errorMsg);
				}
				
			}
		}
	}
	
	

	
	
}
