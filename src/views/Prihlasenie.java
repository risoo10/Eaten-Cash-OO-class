package views;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

import controllers.Router;

public class Prihlasenie implements Nahlad{
	private String id;
	private String[] userTypy;
	
	public Prihlasenie(String[] userTyp){
		userTypy = userTyp;
		this.akcia();
	}
	
	public void akcia(){
		
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
		okno.vymazVsetko();
		
		// nacita a prida logo do Okna
		JLabel logoImg = nacitajLogo("velke");	
		logoImg.setPreferredSize(new Dimension(okno.getWidth(), 250));
		okno.add(logoImg, BorderLayout.NORTH);
		
		// novy Panel 
		JPanel tlacidlaPrihlasenie = new JPanel();
		tlacidlaPrihlasenie.setLayout(new FlowLayout());
		
		// nadpis
		JLabel nadpis = new JLabel("Prihlaste sa :", JLabel.CENTER);
		nadpis.setPreferredSize(new Dimension(okno.getWidth(), 90));
		nadpis.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
		tlacidlaPrihlasenie.add(nadpis);
		
		
		// tlacidla pre prihlasenie v jednom paneli	
		for(int i=0; i< userTypy.length; i++){
			JButton tlacidlo = new JButton(userTypy[i]);
			tlacidlo.setActionCommand("Prihlasenie " + userTypy[i]);
			tlacidlo.setPreferredSize(new Dimension(150, 80));
			tlacidlo.addActionListener(Router.getRouter());
			tlacidlaPrihlasenie.add(tlacidlo);
		}
		
		okno.add(tlacidlaPrihlasenie, BorderLayout.CENTER);
		
		// obnovi zmeny v okne
		okno.repaint();
		okno.revalidate();
		
	}
	
	// *********** Prerob do Controllera
	public static JLabel nacitajLogo(String size) {
		// nacita obrazok a nahra do JLabel logoImg
		// -> ak sa nenajde obrazok, vypise error
		BufferedImage loadImg = null;
		try {
			if(size == "velke")
				loadImg = ImageIO.read(new File("res/Logo_H175px.png"));
			else if(size == "male")
				loadImg = ImageIO.read(new File("res/Logo_H62px.png"));
		} catch (IOException ex){
			System.out.println("Nepodarilo sa nacitat obrazok (Logo).");
			return null;
		}
		
		return new JLabel(new ImageIcon(loadImg));
	}

	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}

}
