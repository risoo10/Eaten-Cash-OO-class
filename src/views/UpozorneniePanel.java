package views;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;


import models.RozosielatelUpozornenia;
import models.SimpleUser;

public class UpozorneniePanel implements Nahlad{
	
	private String id;
	
	public UpozorneniePanel(String text, String rodicId){
		// Referencia na JFrame Okno. Zmenim vertikalnu dlzku okna aby sa zmestil aj panel s Upozornenim dole pod obsahom.
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
		okno.setVyska(okno.getVyska()+80);
		okno.nastavPovodnuVelkost();
		
		// Nacitam Panel ako atribut Okna pre dalsie pracovanie s Panelom.
		JPanel velky = okno.getSpodnyPanel();
		
		// Nastavim vysku panela na potrebnu vysku.
		int vyska = velky.getHeight() + 80;
		velky.setPreferredSize(new Dimension(50, vyska));
		
		// Vytvorim Panel pre upozornenie a nastavim mu presnu velkost a farbu 
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Color.decode("#ffca47")); // ZLTA farba v HEX formate
		upPanel.setPreferredSize(new Dimension(okno.getWidth(), 80));
		upPanel.setLayout(new GridLayout(0, 3, 20, 20));
		
			// Pridam ikonu.
			BufferedImage warnIcon = null;
			try {
				warnIcon = ImageIO.read(new File("res/warn.png"));
				
			} catch (IOException ex){
				System.out.println("Nepodarilo sa nacitat obrazok (Warning).");
				System.exit(1);
			}
			upPanel.add(new JLabel(new ImageIcon(warnIcon)));
			
			// Pridam text Upozornenia. TExt bude tucnym a farba bude biela.
			JLabel textLabel = new JLabel(text);
			textLabel.setFont(new Font(textLabel.getFont().getName(), Font.BOLD, 18));
			upPanel.add(textLabel);

			// Pridam meno rodica.
			JLabel rodicLabel = new JLabel("Poslal: "+rodicId);
			rodicLabel.setFont(new Font(textLabel.getFont().getName(), Font.ITALIC, 16));
			upPanel.add(rodicLabel);
			
		
		// Pridam Panel do spodneho panelu a ten pridam do hlavneho okna.
		velky.add(upPanel, BorderLayout.NORTH);
		okno.add(velky, BorderLayout.SOUTH);
		
		// Ulozim si referenciu na SPodny panel do atributu v Okno.
		okno.setSpodnyPanel(velky);
		okno.obnovZmeny();
		
		
	}
	
	
	
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
