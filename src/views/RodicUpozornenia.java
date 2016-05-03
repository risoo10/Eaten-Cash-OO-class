package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import controllers.Router;
import models.PrehladyStravovania;
import models.SimpleUser;
import models.Upozornenie;
import models.User;

public class RodicUpozornenia implements Nahlad{
	
	private String id;
	
	public RodicUpozornenia(List<Upozornenie> zoznamUpoz, User aktualUser) {
		
		Okno okno = (Okno)MapaNahladov.vratNahlad("Okno");
		
		// Vymazem aktualny panel s obsahom.
		okno.vymazContent();
		
		// Nastavenie vlastnosti noveho panelu
		JPanel pnlWrap = new JPanel();
		pnlWrap.setLayout(new GridLayout(0,2));
		
		// 1. Panel -> Panel s aktualnymi detmi a poslanymi upozorneniami.
			JPanel pnlAkt = new JPanel();
			pnlAkt.setLayout(new GridLayout(0, 1));
			
			for(Upozornenie u : zoznamUpoz){
				// Meno dietata ako label.
				JLabel idLbl = new JLabel(((SimpleUser)u.user()).getId());
				idLbl.setFont(new Font(idLbl.getFont().getName(), Font.PLAIN, 16));
				pnlAkt.add(idLbl);
				
				// Aktualny stav dietata. Prehlad jeho minutych financii.
				JPanel rychlyPrehlad = new JPanel();
				rychlyPrehlad.setLayout(new FlowLayout());
				Map<PrehladyStravovania,Double> mapaPrehladov = ((SimpleUser) u.user()).prehladOFinanciach();
				for(PrehladyStravovania prehlad : mapaPrehladov.keySet()){
					// Pre kazdy prehlad vypise sumu po uprave na spravnu menu.
					rychlyPrehlad.add(new JLabel(prehlad.getCasNazov() +" " + 
								String.format("%.2f", mapaPrehladov.get(prehlad)*aktualUser.getMena().getKurz())));
					// Oddelovac
					rychlyPrehlad.add(new JLabel(" | "));
				}
				
				// Ked prave nie je poslane upozornenie				
				if(u.getText() == null){
					// Tlacidlo s akciou na pridanie upozornenia.
					JButton btnUpozornenieUserID = new JButton("Posli Upozornenie.");
					btnUpozornenieUserID.addActionListener(Router.getRouter());
					btnUpozornenieUserID.setActionCommand("RodicFormularUpozornenie");
					// Pridam vlastnost. ID dietata pod klucom ID.
					btnUpozornenieUserID.putClientProperty("id", ((SimpleUser)u.user()).getId()); 
					pnlAkt.add(btnUpozornenieUserID);
					
				// Ak existuje nejake upozornenie.
				} else {
					
					// Vypise aktualne upozornenie pre dane Dieta.
					JLabel upozornenieLbl = new JLabel(u.getText());
					upozornenieLbl.setFont(new Font(upozornenieLbl.getFont().getName(), Font.ITALIC, 14));
					pnlAkt.add(upozornenieLbl);
					
					// Tlacidlo ktore vymaze existujucu upozornenie.
					JButton btnVymayUserID = new JButton("Vymazat !");
					btnVymayUserID.addActionListener(Router.getRouter());
					btnVymayUserID.setActionCommand("RodicVymazUpozornenie");
					// Pridam vlastnost. ID dietata pod klucom ID.
					btnVymayUserID.putClientProperty("id", ((SimpleUser)u.user()).getId()); 
					pnlAkt.add(btnVymayUserID);	
				}
			}
		
		pnlWrap.add(pnlAkt);
		pnlWrap.add(pnlAkt);
		
		// pridaj Panel do okno.Content
		okno.add(pnlWrap, BorderLayout.CENTER);
		okno.setContent(pnlWrap);
		okno.obnovZmeny();
		
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
