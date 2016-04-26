package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class JedloDoma extends Jedlo{
	
	private String suroviny;
	private String popis;

	public JedloDoma(String miesto, String cas, String nazov, Double cena, String suroviny, String popis) {
		super(miesto, cas, nazov, cena);
		setSuroviny(suroviny);
		setPopis(popis);
	}
	
	// Toto je dalsi konstruktor. Ako parameter posielame pole Stringov.
	public JedloDoma(Map<String, String> data){
		
		super("Doma", data.get("casJedla"), data.get("nazov"),Double.parseDouble(data.get("cena")));
		setSuroviny(data.get("suroviny"));
		setPopis(data.get("popis"));
	}
	
	
	public JedloDoma(){
		
	}
	
	// metoda na vratenie detailov o jedleDoma
	public String vratDetaily(){
		 
		// zakladne udaje
		String detailyString = super.vratDetaily();
		
		//  dodatocne udaje
		detailyString += "Suroviny: "+suroviny+"\n";
		detailyString += "Popis: "+popis+"\n";
		
		return detailyString;
	}
	
	public Map<String, String> vratUdajeMapa(){
		
		Map<String, String> udaje = new LinkedHashMap<>();
		udaje = super.vratUdajeMapa();
		udaje.put("suroviny", this.suroviny);
		udaje.put("popis", this.popis);
		
		return udaje;
	}
	

	public String getSuroviny() {
		return suroviny;
	}

	public void setSuroviny(String suroviny) {
		this.suroviny = suroviny;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

}
