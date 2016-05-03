package controllers;


import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		UserController.chodDomovskaStranka();
	}
	
	// Podla aktualne prihlaseneho pouzivatela sa prihlasi.
	public static void getBackDomov(){
		if(aktualUser instanceof SimpleUser) {
			SimpleUserController.getDomov(aktualUser.getId());
		} else if (aktualUser instanceof Rodic){
			RodicController.getDomov(aktualUser.getId());
		} else if (aktualUser instanceof Master) {
			MasterController.getDomov();
		}
	}
	
	
	// Nacita informacie z modelu a vypise na domovsku stranku s prehladom.
	private static void chodDomovskaStranka(){
		
		// Nastavi okno na povodnu velkost.
		UserDomov nahlad = (UserDomov)MapaNahladov.vratNahlad("UserDomov");
		// Nastavi tlacidla
		PanelTlacidla tlac = new PanelTlacidla();
		tlac.tlacidlaPreUser();
		
		Okno okno = nacitajOkno();
		okno.nastavPovodnuVelkost();
	
		
		// Mapa pre udaje o Prehlade financii.
		Map<PrehladyStravovania, Double> mapaPrehlady = aktualUser.prehladOFinanciach();
		
		// Mapa s modelmi pre vytvorenie tabulky.
		Map<PrehladyStravovania, DefaultTableModel> mapaModelyTab = new LinkedHashMap<>();
		
		for(PrehladyStravovania prehlad : PrehladyStravovania.values()) {
			// Vrati list pre spravny prehlad.
			List<Jedlo> list = ImportJedal.prehladZa(prehlad, aktualUser);
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
			dataJedlo[3] = (String)String.format("%.2f",jedlo.getCena()*aktualUser.getMena().getKurz()) + " " + aktualUser.getMena().getZnak();
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
				UserController.chodDomovskaStranka();
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
		PanelTlacidla pnlTlacidla = new PanelTlacidla();
		pnlTlacidla.tlacidlaPreNoveJedlo();
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
			
			// Vytvori mapu udajov s korektnymi hodnotami.
			Map<String, String> data =  jedlo.vratUdajeMapa(aktualUser.getMena());
			MapaNahladov.pridajNahlad("Detail", new DetailJedla(data, aktualUser.getMena()));
		}
		
		// NAstav tlacidla 
		PanelTlacidla pnl = new PanelTlacidla();
		pnl.tlacidlaPreDetailJedla();
		
	}
	
	public static void toggleFinancnaMena(){
		switch (aktualUser.getMena()){
		case DOLAR:
			aktualUser.setMena(FinancnaMena.EURO);
			break;
		case EURO:
			aktualUser.setMena(FinancnaMena.DOLAR);
			break;
		}
		
		UserController.getBackDomov();
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
