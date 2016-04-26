package models;

public interface RozosielatelLimit {
	public void registrujPosluchaca(PosluchacLimit user);
	
	public void odstranPosluchaca(PosluchacLimit user);
	
	public void upozorniPosluchacov();
	
	public void pridajUpozornenie(Double limit, String typLimitu, PosluchacLimit user);
}
