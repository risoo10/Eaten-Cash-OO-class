package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.Router;

public class PanelTlacidla {
	private Okno okno;
	private JPanel pnlTlacidla;
	
	public PanelTlacidla() {
		okno = (Okno) MapaNahladov.vratNahlad("Okno");
	}
	
	// Vymaze predchazdajuci obsah panelu.
	public void novyPanelTlacidla(){
		
		okno.vymazTlacidla();
		pnlTlacidla = new JPanel();	
		pnlTlacidla.setBackground(okno.getColor());
	}
	
	
	// Ulozi referncie do okna a vlozi panel do okna.
	public void ulozPanelTlacidla(JPanel pnl){
		okno.getHlavicka().add(pnl, BorderLayout.EAST);
		okno.setHlavickaTlacidla(pnl);	
		okno.obnovZmeny();
	}
	
	
	
	
	public void tlacidlaPreUser(){
		// Vymaze aktualne tlacidla a nastavi novy panel		
		novyPanelTlacidla();
		
		// Tlacidlo na zmenenie meny.
		JButton btnToggleMenu = new JButton("$/€");
		btnToggleMenu.setPreferredSize(new Dimension(80, 80));
		btnToggleMenu.addActionListener(Router.getRouter());
		pnlTlacidla.add(btnToggleMenu);
		btnToggleMenu.setActionCommand("toggleMena");
		
		
		// tlacidlo pridat nove jedlo
		JButton btnSpat = new JButton("< SPAT");
		btnSpat.setPreferredSize(new Dimension(100, 80));
		btnSpat.addActionListener(Router.getRouter());
		pnlTlacidla.add(btnSpat);
		btnSpat.setActionCommand("Spat");
		
		// tlacidlo pridat nove jedlo
		JButton btnNoveJedlo = new JButton("+ NOVE JEDLO");
		btnNoveJedlo.setPreferredSize(new Dimension(200, 80));
		btnNoveJedlo.setActionCommand("NoveJedlo");
		btnNoveJedlo.addActionListener(Router.getRouter());
		pnlTlacidla.add(btnNoveJedlo);
		
		// Umiestni panel s tlacidlami a posle referenciu do okna.
		okno.setBtnSpat(btnSpat);
		ulozPanelTlacidla(pnlTlacidla);

		
		
	}
	
	public void tlacidlaPreRodic(){
		// Prebehne nacitanie vsetkych tlacidiel pre obycajneho pouzivatela.
		tlacidlaPreUser();
		
		// Prida sa specialne tlacidlo pre Rodica.
		// Tlacidlo UPOZORNENIA
		JButton btnUpozornenia = new JButton("! UPOZORNENIA !");
		btnUpozornenia.setPreferredSize(new Dimension(150, 80));
		btnUpozornenia.setActionCommand("RodicUpozornenia");
		btnUpozornenia.addActionListener(Router.getRouter());
		pnlTlacidla.add(btnUpozornenia);
		
		ulozPanelTlacidla(pnlTlacidla);		
	}
	
	public void tlacidlaPreMaster(){
		// Prebehne nacitanie vsetkych tlacidiel pre obycajneho pouzivatela.
		tlacidlaPreUser();
		
		// Prida sa specialne tlacidlo pre Mastera.
		// Tlacidlo UPOZORNENIA
		JButton btnUpozornenia = new JButton("! LIMITY !");
		btnUpozornenia.setPreferredSize(new Dimension(150, 80));
		btnUpozornenia.setActionCommand("RodicUpozornenia");
		btnUpozornenia.addActionListener(Router.getRouter());
		pnlTlacidla.add(btnUpozornenia);
		
		ulozPanelTlacidla(pnlTlacidla);		
	}
	
	public void tlacidlaPreNoveJedlo(){
		

		novyPanelTlacidla();
		
		// Nastavi akciu tlacidla spat
		pnlTlacidla.add(okno.getBtnSpat());
		okno.nastavAkciuSpat("UserSpat");
		
		// Prida panel a ulozi referencie.
		ulozPanelTlacidla(pnlTlacidla);
	}
	
	public void tlacidlaPreDetailJedla() {
		novyPanelTlacidla();
		
		// Nastavi akciu tlacidla spat
		pnlTlacidla.add(okno.getBtnSpat());
		okno.nastavAkciuSpat("UserSpat");
		
		// Prida panel a ulozi referencie.
		ulozPanelTlacidla(pnlTlacidla);
	}
}
