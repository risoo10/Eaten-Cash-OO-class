package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class JedloJedalen extends Jedlo{
	
	// jedalen
	private String nazovJedalne;
	private int hodnotenieJedla;

	public JedloJedalen(String miesto, String cas, String nazov, Double cena, String nazovJedalne, int hodnotenieJedalne) {
		super(miesto, cas, nazov, cena);
		setNazovJedalne(nazovJedalne);
		setHodnotenieJedla(hodnotenieJedla);
	}
	
	public JedloJedalen(){
		
	}
	
	// Toto je dalsi konstruktor. Ako parameter posielame pole Stringov.
	public JedloJedalen(Map<String, String> data){
	
		
		super("Jedalen", data.get("casJedla"), data.get("nazov"),Double.parseDouble(data.get("cena")));
		setNazovJedalne(data.get("nazovJedalne"));
		setHodnotenieJedla(Integer.parseInt(data.get("hodnotenieJedla")));
	}
	
	
	// 
	public Map<String, String> vratUdajeMapa(FinancnaMena mena){
		
		Map<String, String> udaje = new LinkedHashMap<>();
		udaje = super.vratUdajeMapa(mena);
		udaje.put("nazovJedalne", this.nazovJedalne);
		udaje.put("hodnotenieJedalne", Integer.toString(this.hodnotenieJedla));
		
		return udaje;
	}

	public int getHodnotenieJedla() {
		return hodnotenieJedla;
	}

	public void setHodnotenieJedla(int hodnotenieJedla) {
		this.hodnotenieJedla = hodnotenieJedla;
	}

	public String getNazovJedalne() {
		return nazovJedalne;
	}

	public void setNazovJedalne(String nazovJedalne) {
		this.nazovJedalne = nazovJedalne;
	}

}
