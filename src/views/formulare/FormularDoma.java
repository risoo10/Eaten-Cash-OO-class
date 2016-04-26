package views.formulare;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;

import views.*;


public class FormularDoma extends JPanel implements Formular{
	
	
	private JTextField nazovJedla = null;
	private String[] casy = {"Ranajky", "Obed", "Vecera"};
	private JComboBox<String> casJedla;
	private JTextField cena;
	private JTextArea suroviny;
	private JTextArea popis;

	
	public FormularDoma(JPanel hlavnyFormular, JTextField nazovJedla){
		
		
		
		// nastavi nazov a miesto jedla
		this.nazovJedla = nazovJedla;
		
			
		JPanel formularDoma = hlavnyFormular;
		formularDoma.setPreferredSize(new Dimension(500, 200));
		
			// label Cas
			formularDoma.add(new JLabel("Vyber cas : "));
			
			// ComboBox Cas
			casJedla = new JComboBox<String>(casy);
			formularDoma.add(casJedla);
			
			// label Cena
			formularDoma.add(new JLabel("    Cena : "));
			
			// textfield Cena
			cena = new JTextField(5);
			formularDoma.add(cena);
			
			// Euro label
			formularDoma.add(new JLabel("€"));
			
			
			// Panel TextArea Suroviny
			JPanel surovinyPanel = new JPanel();
	
				// label Suroviny
				surovinyPanel.add(new JLabel("Pouzite suroviny : "));
			
				// Textarea Suroviny
				suroviny = new JTextArea(4, 20);
				surovinyPanel.add(suroviny);
				formularDoma.add(surovinyPanel);
			
			// Popis 	
			JPanel popisPanel = new JPanel();
			popisPanel.add(new JLabel("Popis : "));
			popis = new JTextArea(4, 20);
			popisPanel.add(popis);
			formularDoma.add(popisPanel);
	}
	
	
	public Map<String, String> vratUdaje() {
		
		Map<String, String> udaje = new LinkedHashMap<>();
		udaje.put("nazov", nazovJedla.getText());
		udaje.put("casJedla", (String)casJedla.getSelectedItem());
		udaje.put("cena", cena.getText());
		udaje.put("suroviny", suroviny.getText());
		udaje.put("popis", popis.getText());
		
		return udaje;
	}



}
