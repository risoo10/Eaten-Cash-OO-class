package models;

import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class ImportJedal {
	
	public static List<Jedlo> prehladZa(PrehladyStravovania casovyUsek, User user ) {
		
		// Vytvori sa list pre vyber. Premenna aktDay bude innformacia o aktualnom case.
		List<Jedlo> novyPrehlad = new ArrayList<>();
		List<Jedlo> listJedalUser = user.vratListJedal();
		
		// Vytvoria s dva kalendare na porovnavanie datumov.
		Calendar kalAkt = Calendar.getInstance();
		Calendar kalPast = Calendar.getInstance();
		
		Date aktDay = new Date();
		kalAkt.setTime(aktDay);
		
		
		// Pre kazdy casovy usek saa nastavi faktor, podla ktoreho sa vytiahnu jedla z listu.
		switch (casovyUsek) {
		case DNES :
			for(Jedlo j : listJedalUser) {
				kalPast.setTime(j.getDatum());
				 // Porovnavanie, zisti ci je rovnaky den.
				if(kalAkt.get(Calendar.YEAR) == kalPast.get(Calendar.YEAR) && 
				kalAkt.get(Calendar.DAY_OF_YEAR) == kalPast.get(Calendar.DAY_OF_YEAR)) {
					novyPrehlad.add(j);
				}
			}
			break;
			
		case VCERA :
			// Kalendar bude nastaveny na vcerjasi den.
			
			for(Jedlo j : listJedalUser) {
				 // Porovnavanie, zisti ci je rovnaky den ako vcerajsi den.
				kalPast.setTime(j.getDatum());
				kalAkt.add(Calendar.DAY_OF_YEAR, -1);;
				if(kalAkt.get(Calendar.YEAR) == kalPast.get(Calendar.YEAR) && 
				kalAkt.get(Calendar.DAY_OF_YEAR) == kalPast.get(Calendar.DAY_OF_YEAR)) {
					novyPrehlad.add(j);
				}
			}			
			
			break;
			
		case TYZDEN : 
			for(Jedlo j : listJedalUser) {
				 // Porovnavanie, zisti ci je rovnaky Tyzden ako aktualny.
				kalPast.setTime(j.getDatum());
				if(kalAkt.get(Calendar.YEAR) == kalPast.get(Calendar.YEAR) && 
				kalAkt.get(Calendar.WEEK_OF_YEAR) == kalPast.get(Calendar.WEEK_OF_YEAR)) {
					novyPrehlad.add(j);
				}
			}			
			break;
			
		case MESIAC:
			for(Jedlo j : listJedalUser) {
				 // Porovnavanie, zisti ci je rovnaky MESIAC ako aktualny.
				kalPast.setTime(j.getDatum());
				if(kalAkt.get(Calendar.YEAR) == kalPast.get(Calendar.YEAR) && 
				kalAkt.get(Calendar.MONTH) == kalPast.get(Calendar.MONTH)) {
					novyPrehlad.add(j);
				}
			}			
			
			break;

		}
		
		// Vrati novy list.
		return novyPrehlad;
	}
	
}
