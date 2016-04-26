package models;

public class SimpleUser extends User implements PosluchacUpozornenia{
	
	public SimpleUser(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public SimpleUser(){
		super();
	}

	private String upozornenie = "Chobooot nesmies jest tolko !!";
	private Rodic rodic;
	private Double limit = 0.0;
	

	
	@Override
	// Nastavi upozornenie podla prijateho textu.
	public void obnovZmeny(String upozornenie) {
		this.upozornenie = upozornenie;		
	}
	
	// Zisti ci bolo poslane upozorneni.
	public boolean checkUpozornenia(){
		if(upozornenie != "")
			return true;
		else return false;
	}
	
	// Zisti ci bol pridany limit.
	public boolean checkLimit(){
		if(limit != 0.0)
			return true;
		else return false;
	}
	

	public Rodic getRodic() {
		return rodic;
	}

	public void setRodic(Rodic rodic) {
		this.rodic = rodic;
	}

	public String getUpozornenie() {
		return upozornenie;
	}

	public Double getLimit() {
		return limit;
	}
	
	

}
