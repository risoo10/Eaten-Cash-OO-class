package models;

public enum FinancnaMena {
	
	// Vsetky polozky su v nasej logike primarne v eurach.
	// Ak sa nastavi dolar tak sa budu prenasobovat vetky hodnoty kurzom.
	
	EURO(1.0, "€"),
	DOLAR(1.1493, "$");
	
	// Kurz aktualny k 2.5.2016
	
	private double kurz;
	private String znak;
	
	private FinancnaMena(double kurz, String znak) {
		this.kurz = kurz;
		this.znak = znak;
	}

	public double getKurz() {
		return kurz;
	}

	public String getZnak() {
		return znak;
	}
	
	
}
