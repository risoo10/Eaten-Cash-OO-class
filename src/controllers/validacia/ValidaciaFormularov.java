package controllers.validacia;

import java.util.Map;

import views.*;

public class ValidaciaFormularov {
		
	public String sprava = null;
	
	public ValidaciaFormularov(){
		
	}
	
	// Konstruktor pre validaciu zakladnych udajov.
	public ValidaciaFormularov(Map<String, String> data){
		
		// Zistim ci exstuju prazdne policka.
		if(!suPrazdnePolia(data)){
			
			// Je cena Double ?? 
			try{
				Double.parseDouble(data.get("cena"));
			} catch(Exception e){
				sprava = "Cena musi byt realne cislo!" + "\n";
			}
			
			validaciaDodatocnyFormular(data);
		}
	}
	
	public void validaciaDodatocnyFormular(Map<String, String> data) {
		
		// Tato metoda bude prekonana v podtridach.
	}

	private boolean suPrazdnePolia(Map<String, String> data){
		
		// Skumam ci su vsetky data aj vyplnene.
		for(String zaznam : data.values()){
			if(zaznam.isEmpty()){
				sprava = "Vypln vsetky textove polia !!!" + "\n";
				return true;
			}
		}
		
		return false;
	}
	
	
	// Metoda vrati chybovu spravu.
	public String validaciaChybovaSprava(){
		return this.sprava;
	}

	

	
	 

}
