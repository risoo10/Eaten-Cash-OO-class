package views.formulare;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.*;


import javax.swing.*;

import controllers.Router;
import models.SimpleUser;
import views.MapaNahladov;
import views.Nahlad;
import views.Okno;

public class PrihlasenieSimpleUsers implements Nahlad {
	
	private String id;
	
	private JTextField novyUserId;
	
	
	// Konstruktor volany pri kazdom zobrazeni informacii
	public PrihlasenieSimpleUsers(List<SimpleUser> simpleUsers) {
		
		// Vytvori novy panel a nastavi velkost, layout a poziciu v okne.
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
		// Vymaz spodny panel
		okno.remove(okno.getSpodnyPanel());
		
		JPanel pnl = new JPanel();
		pnl.setSize(new Dimension(0, 300)); //dolezita je iba Vyska (v BorderLayout sa sirka pri 
											// zobrazeni ako SOUTH prisposobi) 
		pnl.setLayout(new GridLayout(0, 2));
		
		// Vypise 2 Horizontalne ciary
		pnl.add(new JLabel("____________________", JLabel.CENTER));
		pnl.add(new JLabel("____________________", JLabel.CENTER));
		
		// Nadpis pre Existujucich pouzivatelov.
		JLabel ndps1;
		if(simpleUsers.isEmpty()){
			ndps1 = new JLabel("Neexistuju ziadni pouzivatelia !", JLabel.CENTER);
		} else {
			ndps1 = new JLabel("Vyber existujuceho pouzivatela !", JLabel.CENTER);
		}
		ndps1.setFont(new Font(ndps1.getFont().getName(), Font.PLAIN, 16));
		pnl.add(ndps1);
		
		// Nadpis pre Existujucich pouzivatelov.
		JLabel ndps2 = new JLabel("Alebo vytvor noveho pouzivatela.", JLabel.CENTER);
		ndps2.setFont(new Font(ndps2.getFont().getName(), Font.PLAIN, 16));
		pnl.add(ndps2);
		
		// Vytvori mini panel s Tlacidlami pre existujucich pouzivatelov.
		JPanel pnlUsers = new JPanel();
		pnlUsers.setLayout(new FlowLayout());
		for(SimpleUser u : simpleUsers){
			JButton btnUser = new JButton(u.getId());
			btnUser.addActionListener(Router.getRouter());
			btnUser.setActionCommand("SimpleUserGetDomov");
			pnlUsers.add(btnUser);
		}
		pnl.add(pnlUsers);
		
		// Vytvori mini formular pre vytvorenie noveho pouzivatela.
		JPanel pnlNovy = new JPanel();
		pnlNovy.setLayout(new FlowLayout());
		pnlNovy.add(new JLabel("Meno: ", JLabel.RIGHT));
		novyUserId = new JTextField(13);
		pnlNovy.add(novyUserId);
		JButton btnNovyUser = new JButton("Vytvor!");
		btnNovyUser.addActionListener(Router.getRouter());
		btnNovyUser.setActionCommand("NovySimpleUserGetDomov");
		pnlNovy.add(btnNovyUser);
		pnl.add(pnlNovy);
		
		
		// Oddelenie od Okraja okna.
		pnl.add(new JLabel(""));
		
		
		okno.add(pnl, BorderLayout.SOUTH);
		okno.setSpodnyPanel(pnl);
		okno.obnovZmeny();
	}
	
	
	
	
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	public String getUserId(){
		if(novyUserId != null)
			return novyUserId.getText();
		return "";
	}


}
