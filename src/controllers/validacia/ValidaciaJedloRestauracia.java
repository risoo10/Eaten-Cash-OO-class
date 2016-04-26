package controllers.validacia;

import java.util.Map;

public class ValidaciaJedloRestauracia extends ValidaciaFormularov{
	
	public ValidaciaJedloRestauracia(Map<String, String> data){	
		super(data);	
	}
	
	public void validaciaDodatocnyFormular(Map<String, String> data){
		
		// Validacia dodatocnych udajov pe Jedlo Restauracia
		
		// je Hodnotenie restauracie cele cislo od 1 - 5 hodnotenie(data[4])
		int hodnotenie;
		try {
			hodnotenie = Integer.parseInt(data.get("hodnotenieRestauracie"));
		} catch(Exception e){
			super.sprava += "Hodnotenie restauracie musi byt cele cislo.!!" + "\n";
			hodnotenie = 0;
		}
		
		if (!(hodnotenie >= 1 && hodnotenie <= 5)){
			super.sprava += "Hodnotenie restauracie musi byt od 1 po 5 !!" + "\n";
		}
	}
}
