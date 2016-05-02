package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public enum PrehladyStravovania {
	
	DNES("Dnes"), 
	VCERA("Vèera"), 
	TYZDEN("Týždeò"), 
	MESIAC("Mesiac") ;
	
	private String nadpis;
	
	PrehladyStravovania(String nadpis) {
		this.nadpis = nadpis;
	}
	
	public String getNadpis() {
		return nadpis;
	}
	
	public String getCasNazov() {
		Date now = new Date();
		DateFormat formatCas;
		
		switch (this){
		case DNES :
			formatCas = new SimpleDateFormat("EEEE");
			return formatCas.format(now);
			
		case VCERA :
			formatCas = new SimpleDateFormat("EEEE");
			// Vytvori vcerajsi datum.
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date vcera = cal.getTime();
			
			return formatCas.format(vcera);
			
		case TYZDEN :
			formatCas = new SimpleDateFormat("ww");
			return formatCas.format(now) + "." + nadpis;
			
		case MESIAC :
			formatCas = new SimpleDateFormat("MMMM");
			return formatCas.format(now);
		}
		
		return "datum";
	}

}
