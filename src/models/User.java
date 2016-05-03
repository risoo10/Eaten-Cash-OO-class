package models;

import java.io.Serializable;
import java.util.*;


public abstract class User implements Serializable{
		
	protected String id;
	protected List<Jedlo> mojeJedla = new ArrayList<Jedlo>();
	// Povodne bude nastavena mena EURO.
	protected FinancnaMena mena = FinancnaMena.EURO;
	

	// Konstruktor s ID
	public User(String id){
		this.id = id;
	}
	
	// Prazdny konstruktor
	public User(){
		Users users = (Users) MapaObjektov.vratObjekt("users");
		users.addUser(this);
	}
	
	// Metoda vrati Mapu s prehladom o minutych peniazoch pre Usera.
	//  Pre kazdy typ prehladu vytvori Prehlad a Model tabulky a vlozi do map.
	public Map<PrehladyStravovania,Double> prehladOFinanciach() {
			Map<PrehladyStravovania,Double> mapaPrehlady = new LinkedHashMap<>();
			
			for (PrehladyStravovania prehlad : PrehladyStravovania.values()) {
				// Vrati list pre spravny prehlad.
				List<Jedlo> list = ImportJedal.prehladZa(prehlad, this);
				
				// Sumuje setky jedla v liste.
				Double sumaJedal = 0.0;
				for(Jedlo j : list){
					// Pouziva spravny kurz podla pouzivatelom zvolenej meny.
					sumaJedal += j.getCena();
				}
				mapaPrehlady.put(prehlad, sumaJedal);
				
				
			}
			return mapaPrehlady;
	}
	
	
	public FinancnaMena getMena() {
		return mena;
	}
	
	public void setMena(FinancnaMena mena) {
		this.mena = mena;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// vrat typ jedlo z listu podla indexu 
	public Jedlo getJedlo(int index){
		return mojeJedla.get(index);
	}
	
	// vrati od konca jedlo podla indexu 
	public Jedlo getReverseJedlo(int index){
		index = mojeJedla.size() - index - 1; // -1 lebo index je od nuly
		return mojeJedla.get(index);
	}
	
	// prida novy element Jedlo
	public void addJedlo(Jedlo jedlo){
		mojeJedla.add(jedlo);
	}

	// vrati string jedla z listu
	public List<Jedlo> vratListJedal(){
		return mojeJedla;
	}
	
	// odstrani Jedlo podla indexu
	public void removeJedlo(int index){
		mojeJedla.remove(index);
	}
	
	// odstrani Jedlo od opacneho konca z listu
	public void removeReverseJedlo(int index){
		index = mojeJedla.size() -index -1; // -1 lebo index je od nuly
		mojeJedla.remove(index);
	}
}
