package views;

import java.awt.*;
import javax.swing.*;

import controllers.*;
import views.formulare.*;


public class PridajNoveJedlo implements Nahlad {
	
	String id;
	Okno okno;
	private Formular formular;
	
	// udaje ktore zbierame vo formulare
	private JTextField nazovJedla;
	
	private String[] miesta = {"Doma", "Jedalen", "Restauracia"};
	private JComboBox<String> miestoComboBox;
	
	
	// panel DODATOCNY FORMULAR
	private JPanel dodatocnyFormular;
	private JPanel pridanieWrapper;
	
	
	public PridajNoveJedlo(){
		okno = (Okno)MapaNahladov.vratNahlad("Okno");
		akcia();
	}
	
	
	private void akcia() {
		
		// uprava tlacidiel
		okno.vymazTlacidla();
		okno.getHlavickaTlacidla().add(okno.getBtnSpat());
		okno.nastavAkciuSpat("UserSpat");
		
		
		okno.vymazContent();
		// novy panel
		JPanel pridajJedloPanel = new JPanel();
		pridajJedloPanel.setLayout(new FlowLayout());
		
		// nadpis formulara
		JLabel nadpis = new JLabel("PRIDANIE NOVEHO JEDLA", JLabel.CENTER);
		nadpis.setPreferredSize(new Dimension(okno.getWidth(), 60));
		nadpis.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
		pridajJedloPanel.add(nadpis);
		
		// pridanie noveho jedla
		
		// wrapper 
		pridanieWrapper = new JPanel();
		pridanieWrapper.setPreferredSize(new Dimension(600, 450));

			JLabel nazovLabel = new JLabel("Nazov jedla : ");
			pridanieWrapper.add(nazovLabel);
			
			nazovJedla = new JTextField(15);
			pridanieWrapper.add(nazovJedla);

			JLabel miestoLabel = new JLabel("Kde si jedol ? ");
			pridanieWrapper.add(miestoLabel);
			
			miestoComboBox = new JComboBox<String>(miesta);
			miestoComboBox.setSelectedIndex(0);
			pridanieWrapper.add(miestoComboBox);
			
			
			// Pridaj formular pre ziskanie dodatocnych udajov.
			// Prvy bude vzdy pre JedloDoma.
			dodatocnyFormular = new JPanel();
			formular = new FormularDoma(dodatocnyFormular, nazovJedla);
			pridanieWrapper.add(dodatocnyFormular);	
			miestoComboBox.setActionCommand("MiestoComboBox");
			miestoComboBox.addActionListener(Router.getRouter());
			
			
			// Tlacidlo uloz
			JButton dalsiKrok = new JButton("Uloz jedlo"); 
			dalsiKrok.setPreferredSize(new Dimension(500, 60));
			dalsiKrok.setActionCommand("UlozJedlo");
			dalsiKrok.addActionListener(Router.getRouter());
			pridanieWrapper.add(dalsiKrok);
					
			
		
			
	
		pridajJedloPanel.add(pridanieWrapper);	
			
		// pridaj Panel do okno.Content
		okno.add(pridajJedloPanel, BorderLayout.CENTER);
		okno.setContent(pridajJedloPanel);
		okno.obnovZmeny();
	}


	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}
	
	
	// Obnovi zmeny vo Formulari
	public void obnovZmenyFormular(){
		pridanieWrapper.repaint();
		pridanieWrapper.revalidate();
	}
	
	// vymaz Dodatocny Formular 
	public void vymazDodatocnyFormular(){
		dodatocnyFormular.removeAll();
	}
	
	public Formular vratFormular(){
		return this.formular;
	}
	
	public void setFormular(Formular formular){
		this.formular = formular;
	}
	
	public JPanel vratDodatocnyFormular(){
		return this.dodatocnyFormular;
	}
	
	public JTextField vratNazovJedla(){
		return this.nazovJedla;
	}
	
	public String vratMiestoJedla(){
		return (String)this.miestoComboBox.getSelectedItem();
	}
	
	public void  vypisSpravu(String sprava){
		JLabel msg = new JLabel(sprava);
		msg.setFont(new Font(msg.getFont().getName(), Font.ITALIC, msg.getFont().getSize()));
		msg.setForeground(Color.BLUE);
		pridanieWrapper.add(msg);
		okno.obnovZmeny();
	}
	
	
}
