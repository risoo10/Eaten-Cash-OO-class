package models;

public interface RozosielatelUpozornenia {
	
	public void registrujPosluchaca(PosluchacUpozornenia user);
	
	public void odstranPosluchaca(PosluchacUpozornenia user);
	
	public void upozorniPosluchacov();
	
	public void pridajUpozornenie(String upozornenie, PosluchacUpozornenia user);
}
