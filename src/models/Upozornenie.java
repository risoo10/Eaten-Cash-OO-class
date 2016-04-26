package models;

public class Upozornenie {
	private String textUpozornenia;
	private PosluchacUpozornenia user;
	
	public Upozornenie(String text, PosluchacUpozornenia user){
		textUpozornenia = text;
		this.user = user;
	}
	
	public PosluchacUpozornenia user(){
		return user;
	}
	
	public void setText(String text){
		textUpozornenia = text;
	}
	
	public String getText(){
		return textUpozornenia;
	}

}
