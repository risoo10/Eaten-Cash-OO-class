package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Jedlo implements Serializable{
	// tabulka prehladov
	private static String[] tableColumns = {"Miesto", "Cas", "Nazov jedla", "Cena"};
	
	private String miesto;
	private String cas;
	private String nazov;
	private Double cena;
	private Date datum;
	
	public Jedlo(String miesto, String cas, String nazov, Double cena){
		setMiesto(miesto);
		setCas(cas);
		setNazov(nazov);
		setCena(cena);
		datum = new Date();
	}
	
	public Jedlo(){
		
	}
	
	public static String[] getTableColumns(){
		return tableColumns;
	}
	
	// metoda na vratenie detailov o jedle
	public String vratDetaily(){
		
		// metoda bude pretazena v podtriedach 
		// polymorfizmus
		
		String detailyString = "";
		
		detailyString += "********** Nazov: "+nazov+"\n";
		detailyString += "Miesto: "+miesto+"\n";
		detailyString += "Cas: "+cas+"\n";
		detailyString += "Cena: "+Double.toString(cena)+"\n";
		
		return detailyString;
	}	
	
	
	// Vrati udaje v podobe Linkovanej Mapy pre lepsiu praacu s retazcami.
	public Map<String, String> vratUdajeMapa(){
		Map<String, String> data =  new LinkedHashMap<>();
		data.put("nazov", this.nazov);
		data.put("miesto", this.miesto);
		data.put("casJedla", this.cas);
		data.put("cena", Double.toString(this.cena));
		
		// posli aj Datum v spravnom formate
		DateFormat df = new SimpleDateFormat("d. MMMM y"); // d - den v mmesiaci, MMMM - mesiac slovom, y - rok ciselne
		data.put("datum", df.format(datum));
		
		return data;
	}
	
	public String getMiesto() {
		return miesto;
	}
	public void setMiesto(String miesto) {
		this.miesto = miesto;
	}
	public String getCas() {
		return cas;
	}
	public void setCas(String cas) {
		this.cas = cas;
	}
	public String getNazov() {
		return nazov;
	}
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
}
