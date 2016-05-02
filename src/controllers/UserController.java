package controllers;


import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import controllers.validacia.*;
import models.*;

import views.*;
import views.formulare.*;


public class UserController {
	
	protected Users users;
	protected static User aktualUser;
	
	
	public static Okno nacitajOkno(){
		return (Okno)MapaNahladov.vratNahlad("Okno");
	}	
	
	// Vytvori prvy krat domovsku stranku.
	public static void getDomov(){
		
		MapaNahladov.pridajNahlad("UserDomov", new UserDomov());
		UserController.getDomovBack();
	}
	
	// Nacita informacie z modelu a vypise na domovsku stranku s prehladom.
	public static void getDomovBack(){
		
		// Nastavi okno na povodnu velkost.
		UserDomov nahlad = (UserDomov)MapaNahladov.vratNahlad("UserDomov");
		Okno okno = nacitajOkno();
		okno.nastavPovodnuVelkost();
		
		// Mapa pre udaje o Prehlade financii.
		// Mapa s modelmi pre vytvorenie tabulky.
		Map<PrehladyStravovania, Double> mapaPrehlady = new LinkedHashMap<>();
		Map<PrehladyStravovania, DefaultTableModel> mapaModelyTab = new LinkedHashMap<>();
		
		// Pre kazdy typ prehladu vytvori Prehlad a Model tabulky a vlozi do map.
		for (PrehladyStravovania prehlad : PrehladyStravovania.values()) {
			// Vrati list pre spravny prehlad.
			List<Jedlo> list = ImportJedal.prehladZa(prehlad, aktualUser);
			
			// Sumuje setky jedla v liste.
			Double sumaJedal = 0.0;
			for(Jedlo j : list){
				sumaJedal += j.getCena();
			}
			
			mapaPrehlady.put(prehlad, sumaJedal);
			mapaModelyTab.put(prehlad, vytvorModelTabulky(list));	
			
		}		
		
		nahlad.akcia(aktualUser, mapaPrehlady, mapaModelyTab );
		
	}
	
	// vytvor table model z aktualneho pouzivatela
	private static DefaultTableModel vytvorModelTabulky(List<Jedlo> list){
		String[] columns = {"Nazov", "Miesto", "Cas", "Cena", "Akcia"};
		DefaultTableModel tableModelAll = new DefaultTableModel(null, columns );
		
		for(Jedlo jedlo: list){
			Object[] dataJedlo = new Object[5];
			dataJedlo[0] = jedlo.getNazov();
			dataJedlo[1] = jedlo.getMiesto();
			dataJedlo[2] = jedlo.getCas();
			dataJedlo[3] = (String)Double.toString(jedlo.getCena()) + " €";
			dataJedlo[4] = "vymazat";
			tableModelAll.insertRow(0, dataJedlo);
		}
		
		return tableModelAll;
		
	}
	
	// vytvor akciu pre vymazanie riadku z tabulky 
	public static Action akciaVymazRiadok(){

		Action vymazRiadok = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable)e.getSource();
				
				// e.getActionCommand vrati index riadku v ktorom bolo stlacene vymaz
				int modelRow = Integer.valueOf(e.getActionCommand()); 
				
				// vymaze zaznam z listu jedal
				aktualUser.removeReverseJedlo(modelRow);
				
				// Zobrazi domovsku stranku.
				UserController.getDomovBack();
			}
			
		};
		
	
		return vymazRiadok;
	}
	
	
	
	
	
	// start programu
	public static void getPrihlasenie(){
		Prihlasenie prihlasenie = (Prihlasenie)MapaNahladov.vratNahlad("Prihlasenie");
		prihlasenie.akcia();
		
		
		// Ruchlo vuis setkych userov do konzoly
		
	}
	
	
	
	
	
	// pridaj Nahlad na pridavanie noveho jedla
	public static void getPridajNoveJedlo(){
		MapaNahladov.pridajNahlad("PridajJedloNahlad", new PridajNoveJedlo());
	}
	
	// uloz nove jedlo
	public static void ulozNoveJedlo(){
		// Nacitaj udaje z formulara.
		PridajNoveJedlo formular = (PridajNoveJedlo)MapaNahladov.vratNahlad("PridajJedloNahlad");
		String miesto = formular.vratMiestoJedla();
		
		
		
		// VALIDACIA UDAJOV 
		Formular formularData = formular.vratFormular();
		Map<String,String> suroveData = formularData.vratUdaje();
		ValidaciaFormularov validaciaNoveJedlo = null; 
		
		// Podla typu formulara pridelim spravnu validaciu.
		if(formularData instanceof FormularDoma){
			validaciaNoveJedlo = new ValidaciaFormularov(suroveData);
		} else if(formularData instanceof FormularJedalen){
			validaciaNoveJedlo = new ValidaciaJedloJedalen(suroveData);
		} else if(formularData instanceof FormularRestauracia){
			validaciaNoveJedlo = new ValidaciaJedloRestauracia(suroveData);
		}
		
		String errorMessage = validaciaNoveJedlo.validaciaChybovaSprava();
		
		if(errorMessage != null){
			
			JOptionPane.showMessageDialog(null, errorMessage);
			
		} else {
		
			Jedlo noveJedlo = null;
		
			if(formularData instanceof FormularDoma){
				noveJedlo = new JedloDoma(suroveData);
				
				
			} else if(formularData instanceof FormularJedalen){
				noveJedlo = new JedloJedalen(suroveData);
				
				
			} else if(formularData instanceof FormularRestauracia){
				noveJedlo = new JedloRestauracia(suroveData);
			}
			
			aktualUser.addJedlo(noveJedlo);
		}
		
		// Vrat spravu o tom ze jedlo bolo ulozene
		formular.vypisSpravu("Nove jedlo bolo ulozene !");
		
		
		
	}
	
	
	
	
	public static void zobrazDetailJedla(int row){
		// zisti ci existuje zaznam v liste jedal a ci existuje taky riadok
		List<Jedlo> jedla = aktualUser.vratListJedal();
		
		if(jedla.size() != 0 && row != -1){
			Jedlo jedlo = aktualUser.getReverseJedlo(row);
			
			Map<String, String> udajeJedlo = jedlo.vratUdajeMapa();
			
			MapaNahladov.pridajNahlad("Detail", new DetailJedla(udajeJedlo));
		}
	}
	
	
	protected static String ovalidujNovehoUzivatela(String userId) {
		// Ak nepreslo UserId validaciou, tak vyhodi upozornenie
		String errorMsg = null;
		Users users = (Users)MapaObjektov.vratObjekt("users");
		
		if(userId.isEmpty()) {
			errorMsg = "Pole MENO: nesmie zostat prazdne!";
			JOptionPane.showMessageDialog(null, errorMsg);
		} 
		
		if(! users.isUnique(userId)){
			errorMsg = "Meno | " + userId + " | sa uz pouziva. Zadajte ine meno !";
			JOptionPane.showMessageDialog(null, errorMsg);
		}
		
		return errorMsg;
	}
	
}
