package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.OknoController;
import controllers.Router;

public class Okno extends JFrame implements Nahlad{
	

	private static final long serialVersionUID = 1L;
	
	public Router router = new Router();
	private String id;
	private int vyska = 700;
	private Color color = Color.WHITE;
	



	// na upravovanie pocas bezania programu ... referencia
	private JButton btnSpat;
	private JPanel hlavickaTlacidla;
	private JPanel hlavicka;
	private JPanel content;
	private JPanel spodnyPanel = null;
	
	
	public Okno(){
		super("EatenCash");
		this.setSize(960, vyska);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new OknoController());
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		spodnyPanel = new JPanel();
		spodnyPanel.setLayout(new BorderLayout());
	}
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void vymazVsetko(){
		this.getContentPane().removeAll();
	}
	
	public void vymazContent(){
		this.getContentPane().remove(content);
	}
	
	public void obnovZmeny(){
		this.repaint();
		this.revalidate();
	}
	
	// nastavi action command tlacidla SPAT	
	public void nastavAkciuSpat(String akcia){
		btnSpat.setActionCommand(akcia);
	}
	
	// vymaz tlacidlaHlavicka 
	public void vymazTlacidla(){
		this.hlavicka.remove(hlavickaTlacidla);
	}
	
	public void nastavPovodnuVelkost(){
		this.setSize(960, vyska);
		spodnyPanel.setSize(new Dimension(0, 0));
	}
	
	public void nastavPovodnuVelkost(int newVyska){
		vyska = newVyska;
		this.setSize(960, vyska);
		spodnyPanel.setSize(new Dimension(0, 0));
	}

	
	public int getVyska() {
		return vyska;
	}


	public void setVyska(int vyska) {
		this.vyska = vyska;
	}


	// gettery a settery pre HLAVICKA, CONTENT, HLAVICKATLACIDLA, BTNSPAT
	public JPanel getHlavicka() {
		return hlavicka;
	}
	public void setHlavicka(JPanel hlavicka) {
		this.hlavicka = hlavicka;
	}
	
	public JPanel getHlavickaTlacidla() {
		return hlavickaTlacidla;
	}
	public void setHlavickaTlacidla(JPanel hlavickaTlacidla) {
		this.hlavickaTlacidla = hlavickaTlacidla;
	}
	
	public JButton getBtnSpat() {
		return btnSpat;
	}
	public void setBtnSpat(JButton btnSpat) {
		this.btnSpat = btnSpat;
	}
	
	public JPanel getContent() {
		return content;
	}
	public void setContent(JPanel content) {
		this.content = content;
	}


	public JPanel getSpodnyPanel() {
		return spodnyPanel;
	}


	public void setSpodnyPanel(JPanel spodnyPanel) {
		this.spodnyPanel = spodnyPanel;
	}
	
	public Color getColor() {
		return color;
	}
	
	
	
	
		
}
