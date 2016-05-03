package models;

import java.util.*;

public class Rodic extends User implements RozosielatelUpozornenia {
	
	public Rodic(String id) {
		super(id);
	}
	
	private List<Upozornenie> upozornenia = new ArrayList<Upozornenie>();
	
	// Vrati list vsetkych upozorneni.
	public List<Upozornenie> getUpozornenia() {
		return upozornenia;
	}
	
	// posli upozornenie pouzivatelovi podla userId
	void posliUpozornenie(int userId){
		
	}
	// Odstran upozornenie podla userId
	void removeUpozornenie(int userId){
		
	}
	
	@Override
	public void registrujPosluchaca(PosluchacUpozornenia user) {
		upozornenia.add(new Upozornenie(user));		
	}
	
	@Override
	public void odstranPosluchaca(PosluchacUpozornenia user) {
		for(Upozornenie up : upozornenia){
			if (up.user() == user)
				upozornenia.remove(up);
		}
	}
	@Override
	public void upozorniPosluchacov() {
		for(Upozornenie up : upozornenia){
			up.user().obnovZmeny(up.getText());
		}
		
	}
	@Override
	public void pridajUpozornenie(String upozornenie, PosluchacUpozornenia user) {
		for(Upozornenie up : upozornenia){
			if (up.user() == user){
				up.setText(upozornenie);
				this.upozorniPosluchacov();
			}	
		}
	}
	
}
