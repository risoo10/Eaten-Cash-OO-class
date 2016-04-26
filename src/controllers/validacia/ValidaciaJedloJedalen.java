package controllers.validacia;

import java.util.Map;

public class ValidaciaJedloJedalen extends ValidaciaFormularov{
	
	public ValidaciaJedloJedalen(Map<String, String> data){	
		super(data);	
	}
	
	public void validaciaDodatocnyFormular(Map<String, String> data){
		
		// Validacia dodatocnych udajov pe Jedlo Jedalen
		
		// je Hodnotenie jedla cele cislo od 1 - 5 hodnotenie(data[4])
		int hodnotenie;
		try {
			hodnotenie = Integer.parseInt(data.get("hodnotenieJedla"));
		} catch(Exception e){
			super.sprava += "Hodnotenie jedla musi byt cele cislo.!!" + "\n";
			hodnotenie = 0;
		}
		
		if (!(hodnotenie >= 1 && hodnotenie <= 5)){
			super.sprava += "Hodnotenie jedla musi byt od 1 po 5 !!" + "\n";
		}
	}
	
}
