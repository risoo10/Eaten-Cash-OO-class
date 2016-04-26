package views;

public class LimitPanel implements Nahlad{
	
	private String id;
	
	
	// Konstruktor pre vytvoreniePanelu
	public LimitPanel(Double limit, Double aktStav, String typLimitu ){
		
	}
	
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;		
	}

}
