package views;

import java.awt.*;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.*;

import controllers.*;
import models.*;

public class UserDomov extends JPanel implements Nahlad{
	

	private static final long serialVersionUID = 1L;
	
	private static Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
	private static String id;
	
	public static final int COLUMN_BUTTON = 4; 
	
	public UserDomov(){
		okno.vymazVsetko();
		
		// panel Hlavicka User
		JPanel hlavicka = new JPanel();
		hlavicka.setBackground(Color.WHITE);
		hlavicka.setLayout(new BorderLayout());
		
		// prida label Logo
		JLabel miniLogo = Prihlasenie.nacitajLogo("male");
		miniLogo.setPreferredSize(new Dimension(300,80));
		hlavicka.add(miniLogo, BorderLayout.WEST);
		
		// panel pre tlacidla 
		JPanel tlacidlaHlavicka = new JPanel();
		tlacidlaHlavicka.setLayout(new FlowLayout());
		tlacidlaHlavicka.setBackground(Color.WHITE);
		

		// referencia o paneli tlacidiel v Okne
		okno.setHlavickaTlacidla(tlacidlaHlavicka);
		
		// referencia o hlavicke do Okna
		okno.setHlavicka(hlavicka);

		hlavicka.add(tlacidlaHlavicka, BorderLayout.EAST);
		okno.add(hlavicka, BorderLayout.NORTH);
		okno.setContent(new JPanel());
	}
	
	public void akcia(User aktualUser, Map<PrehladyStravovania, Double> mapaPrehlady, 
			Map<PrehladyStravovania, DefaultTableModel> mapaModelTab ){			
		
		
		// vymaze predchadzajuci Obsah 
		okno.vymazContent();
		
		
		// Novy Panel pre Domov USer
		JPanel novyUserDomov = new JPanel();
		novyUserDomov.setLayout(new FlowLayout());
		
		
		// Nadpis 
		JLabel nadpisPrehlad = new JLabel("PREHLAD STRAVOVANIA", JLabel.CENTER);
		nadpisPrehlad.setPreferredSize(new Dimension(okno.getWidth(), 60));
		nadpisPrehlad.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
		novyUserDomov.add(nadpisPrehlad);
		
		
		// Panel so strucnym prehladom o prejedenych peniazoch.
		// Pre vsetky typy prehladov.
		
		JPanel pnlPrehlad = new JPanel(new GridLayout(3,0));
		// Vypise vsetky nadpisy panelov.
		for(PrehladyStravovania prehlad : mapaPrehlady.keySet()){
			JLabel prehladLbl = new JLabel(prehlad.getNadpis());
			prehladLbl.setFont(new Font(prehladLbl.getFont().getName(), Font.BOLD, 18));
			prehladLbl.setForeground(Color.DARK_GRAY);	
			pnlPrehlad.add(prehladLbl);
		}
		
		// Vypise den / tyzden / mesiac nad cenu.
		for(PrehladyStravovania prehlad : mapaPrehlady.keySet()){
			JLabel cas = new JLabel(prehlad.getCasNazov());
			cas.setForeground(Color.gray);
			cas.setBackground(new Color(0,0,0,0));
			pnlPrehlad.add(cas);
		}
		
		// Vypise vsetky sumy	
		for(PrehladyStravovania prehlad : mapaPrehlady.keySet()){
			// Pouzivam spravny znak podla nastavenej financnej meny.
			JLabel suma = new JLabel(String.format("%.2f", mapaPrehlady.get(prehlad)*aktualUser.getMena().getKurz()) + " " + aktualUser.getMena().getZnak());
			pnlPrehlad.add(suma);			
		}
		
		pnlPrehlad.setPreferredSize(new Dimension(500, 60));
		
		novyUserDomov.add(pnlPrehlad);
		
		
		
		// vytvor karty pre prehlad
		JTabbedPane kartyPrehlad = new JTabbedPane();
		
			// KARTA 1 - DNES
			JPanel prehlad = new JPanel();
			JTable tabulkaData = new JTable(mapaModelTab.get(PrehladyStravovania.DNES));
			
			// Nastavi vysku riadku tabulky a zarovna obsah na stred.
			DefaultTableCellRenderer df = new DefaultTableCellRenderer();
			df.setHorizontalAlignment(SwingConstants.CENTER);
			tabulkaData.setDefaultRenderer(Object.class, df);
			tabulkaData.setRowHeight(30);
			
			// Nastavi text v prvom riadku na tucne.
			DefaultTableCellRenderer dbold = new DefaultTableCellRenderer(){
				public Component getTableCellRendererComponent(JTable table,
					       Object value, boolean isSelected, boolean hasFocus, int row, int column)
					    {
					        Component c = super.getTableCellRendererComponent(table, value,
					          isSelected, hasFocus, row, column);
					        c.setFont(new Font(c.getFont().getName(), Font.BOLD, c.getFont().getSize()));
					        return c;
					    }
			};
			dbold.setHorizontalAlignment(SwingConstants.CENTER);
			tabulkaData.getColumnModel().getColumn(0).setCellRenderer(dbold);
			
			
			// nastavi 5ty riadok na tlacidla a prida akciu
			ButtonColumn btnClmn = new ButtonColumn(tabulkaData, UserController.akciaVymazRiadok(), 4);
			
			tabulkaData.setPreferredScrollableViewportSize(new Dimension(800, 350));
			tabulkaData.setFillsViewportHeight(true);
			tabulkaData.addMouseListener(RouterMouse.getRouterMouse());
			JScrollPane tabulka1 = new JScrollPane(tabulkaData);
			prehlad.setLayout(new BorderLayout());
			prehlad.add(new JLabel("Pre viac informacii o jedle klikni na riadok"), BorderLayout.NORTH);
			prehlad.add(tabulka1, BorderLayout.CENTER);
			
			kartyPrehlad.add("Dnes", prehlad);
	
		
		novyUserDomov.add(kartyPrehlad);
		
		
		// obnov zmeny v okne
		okno.add(novyUserDomov, BorderLayout.CENTER);
		okno.setContent(novyUserDomov); 
		okno.obnovZmeny();
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}
		
		

}
