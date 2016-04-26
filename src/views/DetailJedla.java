package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailJedla extends JPanel implements Nahlad{
	
	private String id;
	
	public DetailJedla(Map<String, String> udaje){
		
		
		// Upravim panel s tlacidlami a zmenim akciu tlacidla SPAT.
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
		okno.vymazTlacidla();
		okno.getHlavickaTlacidla().add(okno.getBtnSpat());
		okno.nastavAkciuSpat("UserSpat");
		
		// Vymazem aktualny panel s obsahom.
		okno.vymazContent();
		
		// Nastavenie vlastnosti panelu Detail.
		this.setLayout(new FlowLayout());
		
		
		// Zakladne udaje idu tu.
		JPanel zakladneUdaje = new JPanel();
		zakladneUdaje.setLayout(new GridLayout(0, 1, 15, 0));
		
			// Toto je Nadpis
			JLabel nadpis = new JLabel("Nazov jedla: " + udaje.get("nazov"), JLabel.LEFT );
			nadpis.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));
			nadpis.setPreferredSize(new Dimension(okno.getWidth()-100, 30));
			zakladneUdaje.add(nadpis);
		
			// Horizontalny oddelovac.
			zakladneUdaje.add(new JLabel("______"));
		
			// Udaj MIESTO
			JLabel miesto = new JLabel("Miesto kde si jedol : " + udaje.get("miesto"), JLabel.LEFT);
			miesto.setFont(new Font(miesto.getFont().getName(), Font.BOLD, 16));
			zakladneUdaje.add(miesto);
			
			// Udaj Cas + Datum
			JLabel cas = new JLabel(udaje.get("casJedla") +", "+ udaje.get("datum"), JLabel.LEFT);
			cas.setFont(new Font(cas.getFont().getName(), Font.ITALIC, 16));
			zakladneUdaje.add(cas);
			
			// Udaj CENA
			JLabel cena = new JLabel("Jedol si za : " + udaje.get("cena") + " €", JLabel.LEFT);
			cena.setFont(new Font(cena.getFont().getName(), Font.BOLD, 16));
			//cena.setPreferredSize(new Dimension(zakladneUdaje.getWidth(), 60));
			zakladneUdaje.add(cena);
			
			// Horizontalny oddelovac.
			zakladneUdaje.add(new JLabel("______"));		
			
			
			// Teraz vypisem dodatocne udaje z Mapy udajov.
			// Ak sa tam nachadza v mape hodnota s danu referenciou tak ju vypise. 
			// Tym padom sa vypisu iba hodnoty VYZNACNE pre dany typ jedla.
			// Ked Map<String, String> mapa a mapa.get("nazov") == NULL  =>  neexistuje hodnota v mape pre toto id.
			
			
			// Ak je typu JEdloDoma
			if(udaje.get("suroviny") != null){
				
				// Udaj SUROVINY
				JLabel surovinyLabel = new JLabel("Pouzite suroviny :", JLabel.LEFT);
				surovinyLabel.setFont(new Font(surovinyLabel.getFont().getName(), Font.BOLD, 16));
				zakladneUdaje.add(surovinyLabel);
				
				JTextArea suroviny = new JTextArea(udaje.get("suroviny"));
				suroviny.setEditable(false);
				suroviny.setSize(300, 90);
				zakladneUdaje.add(suroviny);
				
				pridajPrazdnyRiadok(zakladneUdaje);
			}
			
			if(udaje.get("popis") != null){
				
				// Udaj POPIS
				JLabel popisLabel = new JLabel("Popisok k jedlu :", JLabel.LEFT);
				popisLabel.setFont(new Font(popisLabel.getFont().getName(), Font.BOLD, 16));
				zakladneUdaje.add(popisLabel); 
				
				JTextArea popis = new JTextArea(udaje.get("popis"));
				popis.setEditable(false);
				popis.setSize(300, 90);
				zakladneUdaje.add(popis);
				
			}
			
			
			// Ak je typu JedloJedalen.
			
			if(udaje.get("nazovJedalne") != null){
				// Udaj Nazov Jedalne.
				JLabel	jedalenLabel = new JLabel("Nazov jedalne :", JLabel.LEFT);
				jedalenLabel.setFont(new Font(jedalenLabel.getFont().getName(), Font.PLAIN, 16));
				zakladneUdaje.add(jedalenLabel);
				
				JLabel	jedalen = new JLabel(udaje.get("nazovJedalne"), JLabel.LEFT);
				jedalen.setFont(new Font(jedalen.getFont().getName(), Font.BOLD, 16));
				zakladneUdaje.add(jedalen);
				
				pridajPrazdnyRiadok(zakladneUdaje);
			}
			
			if(udaje.get("hodnotenieJedalne") != null){
				// Udaj Hodnotenie Jedalne
				
				JLabel	hodnotenieJedlaLabel = new JLabel("Hodnotenie jedla :", JLabel.LEFT);
				hodnotenieJedlaLabel.setFont(new Font(hodnotenieJedlaLabel.getFont().getName(), Font.PLAIN, 16));
				zakladneUdaje.add(hodnotenieJedlaLabel);			
				
				// 
				int hodnotenie = Integer.parseInt(udaje.get("hodnotenieJedalne"));
				
				// Pridam panel Hodnotenie Wrapper. 
				// Hodnotenie vykreslim ako hviezdicky pomocou metody hodnotenieHviezdicky().
				zakladneUdaje.add(hodnotenieHviezdicky(hodnotenie));
			
			}
			
			// Ak je jedlo typu JedloREstauracia
			
			if(udaje.get("nazovRestauracie") != null){
				// Udaj Nazov REstauracie
				JLabel	nazovRestikyLabel = new JLabel("Nazov Restauracie :", JLabel.LEFT);
				nazovRestikyLabel.setFont(new Font(nazovRestikyLabel.getFont().getName(), Font.PLAIN, 16));
				zakladneUdaje.add(nazovRestikyLabel);
				
				JLabel	nazovRestiky = new JLabel(udaje.get("nazovRestauracie"), JLabel.LEFT);
				nazovRestiky.setFont(new Font(nazovRestiky.getFont().getName(), Font.BOLD, 16));
				zakladneUdaje.add(nazovRestiky);
				
				
				pridajPrazdnyRiadok(zakladneUdaje);
			}
			
			if(udaje.get("hodnotenieRestauracie") != null){
				// Udaj Hodnotenie Restauracie
				
				JLabel	hodnotenieRestikyLabel = new JLabel("Hodnotenie restauracie :", JLabel.LEFT);
				hodnotenieRestikyLabel.setFont(new Font(hodnotenieRestikyLabel.getFont().getName(), Font.PLAIN, 16));
				zakladneUdaje.add(hodnotenieRestikyLabel);			
				
				// 
				int hodnotenie = Integer.parseInt(udaje.get("hodnotenieRestauracie"));
				
				// Pridam panel Hodnotenie Wrapper. 
				// Hodnotenie vykreslim ako hviezdicky pomocou metody hodnotenieHviezdicky().
				zakladneUdaje.add(hodnotenieHviezdicky(hodnotenie));
				
				pridajPrazdnyRiadok(zakladneUdaje);
			}
			
			if(udaje.get("tip") != null){
				// Udaj Tip na dalsie jedlo
				JLabel	tipLabel = new JLabel("Tvoj tip na dalsie jedlo :", JLabel.LEFT);
				tipLabel.setFont(new Font(tipLabel.getFont().getName(), Font.PLAIN, 16));
				zakladneUdaje.add(tipLabel);
				
				JLabel	tip = new JLabel(udaje.get("tip"), JLabel.LEFT);
				tip.setFont(new Font(tip.getFont().getName(), Font.BOLD, 16));
				zakladneUdaje.add(tip);
			}
			
		
			
			
			
			
		this.add(zakladneUdaje);
		
		
		// pridaj Panel do okno.Content
		okno.add(this, BorderLayout.CENTER);
		okno.setContent(this);
		okno.obnovZmeny();
	}

	private void pridajPrazdnyRiadok(JPanel zakladneUdaje) {	
		zakladneUdaje.add(new JLabel(""));
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
	
	private JPanel hodnotenieHviezdicky(int hodnotenie){
		
		JPanel hodnotenieWrapper = new JPanel();
		hodnotenieWrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		// Nacitam obrazky na vykreslenie hodnotenia.
		BufferedImage hviezdaSeda = null;
		BufferedImage hviezdaZelena = null;
		try {
			hviezdaSeda = ImageIO.read(new File("res/star.png"));
			hviezdaZelena = ImageIO.read(new File("res/starGreen.png"));
			
		} catch (IOException ex){
			System.out.println("Nepodarilo sa nacitat obrazok (Hviezda).");
		}
		
		// Nacitane hviezdicky podla hodnotenia vypisem.
		if(hviezdaSeda != null && hviezdaZelena != null){
			int i; // Iterator
			for(i=1; i<= hodnotenie; i++){
				hodnotenieWrapper.add(new JLabel(new ImageIcon(hviezdaZelena)));
			}
			for(int k = i; k<= 5; k++){
				hodnotenieWrapper.add(new JLabel(new ImageIcon(hviezdaSeda)));
			}
		}
		
		// Este pridam na konci do zatvorky hodnotenie v desiatkovej sustave.
		hodnotenieWrapper.add(new JLabel("("+Integer.toString(hodnotenie)+")"));
		
		return hodnotenieWrapper;
	}
	
	
	

}
