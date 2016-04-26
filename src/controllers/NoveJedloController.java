package controllers;

import views.*;
import views.formulare.*;

public class NoveJedloController {
	

	public static void comboBoxPanel(String activeValue){
		PridajNoveJedlo nahlad = (PridajNoveJedlo)MapaNahladov.vratNahlad("PridajJedloNahlad");		
		
		switch(activeValue){
		case "Doma":
			nahlad.vymazDodatocnyFormular();
			nahlad.setFormular(new FormularDoma(nahlad.vratDodatocnyFormular(), nahlad.vratNazovJedla()));
			nahlad.obnovZmenyFormular();
			break;
			
		case "Jedalen":
			nahlad.vymazDodatocnyFormular();
			nahlad.setFormular(new FormularJedalen(nahlad.vratDodatocnyFormular(), nahlad.vratNazovJedla()));
			nahlad.obnovZmenyFormular();
			break;
			
		case "Restauracia":
			nahlad.vymazDodatocnyFormular();
			nahlad.setFormular(new FormularRestauracia(nahlad.vratDodatocnyFormular(), nahlad.vratNazovJedla() ));
			nahlad.obnovZmenyFormular();
			break;
		}
	}
}
