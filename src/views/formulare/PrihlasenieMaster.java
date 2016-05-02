package views.formulare;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import controllers.Router;
import views.*;

public class PrihlasenieMaster implements Nahlad{
	
	private String id;
	
	
	// Ulozi vstupne udaje.
	private JTextField username;
	private JPasswordField password;
	
	
	public PrihlasenieMaster() {
		// Vytvori novy panel a nastavi velkost, layout a poziciu v okne.
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
				
		// Vymaz spodny panel
		okno.remove(okno.getSpodnyPanel());
		
		JPanel pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(250, 200));
		
		pnl.setLayout(new GridLayout(0, 1));
		
		// Vypise 2 Horizontalne ciary
		pnl.add(new JLabel("____________________", JLabel.CENTER));
		
		// Nadpis
		JLabel nadpis1 = new JLabel("Prihl·s sa ako Master.", JLabel.CENTER);
		nadpis1.setFont(new Font(nadpis1.getFont().getName(), Font.PLAIN, 16));
		pnl.add(nadpis1);
		
		// Username Textfield pre vstup
		username = new JTextField("Prihlasovacie meno");
			// Po kliknuti sa prebezny text vymaze.
			username.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            username.setText("");
		        }
				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
				}
		    });
		pnl.add(username);	
		
			
		// HESLO label + Textfield pre vstup
		password = new JPasswordField("heslo");
			// Po kliknuti sa prebezny text vymaze.
			password.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            password.setText("");
		        }
				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
				}
		    });
		pnl.add(password);
		
		// Tlacidlo pre skontrolovanie udajov MASTERA.
		JButton btnPrihlasMaster = new JButton("Prihlasiù sa !");
		btnPrihlasMaster.addActionListener(Router.getRouter());
		btnPrihlasMaster.setActionCommand("MasterPrihlasDomov");
		pnl.add(btnPrihlasMaster);
		
		
		
		// Oddelenie od Okraja okna.
		pnl.add(new JLabel(""));
						
		JPanel pnlWrapper = new JPanel();
		pnlWrapper.add(pnl);
		okno.add(pnlWrapper, BorderLayout.SOUTH);
		okno.setSpodnyPanel(pnlWrapper);
		okno.obnovZmeny();

		
		
	}
	
	
	
	public JTextField getUsername() {
		return username;
	}



	public JTextField getPassword() {
		return password;
	}



	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

}
