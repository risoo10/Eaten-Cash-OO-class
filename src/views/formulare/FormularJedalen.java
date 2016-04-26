package views.formulare;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;


public class FormularJedalen extends JPanel implements Formular{
	
	private JTextField nazovJedla = null;
	private String[] casy = {"Ranajky", "Obed", "Vecera"};
	private JComboBox<String> casJedla;
	private JTextField cena;
	private JTextField nazovJedalne;
	private JTextField hodnotenieJedla;
	
	
	public FormularJedalen(JPanel dodatocnyFormular, JTextField nazovJedla){
		
		this.nazovJedla = nazovJedla;
		
		JPanel formularJedalen = dodatocnyFormular;
		formularJedalen.setPreferredSize(new Dimension(500, 200));
		
			// label Cas
			formularJedalen.add(new JLabel("Vyber cas : "));
			
			// ComboBox Cas
			casJedla = new JComboBox<String>(casy);
			formularJedalen.add(casJedla);
			
			// label Cena
			formularJedalen.add(new JLabel("    Cena : "));
			
			// textfield Cena
			cena = new JTextField(5);
			formularJedalen.add(cena);
			
			// Euro label
			formularJedalen.add(new JLabel("€"));
			
			
			// Panel TextArea Nazov
			JPanel nazovJedalnePanel = new JPanel();
			nazovJedalnePanel.add(new JLabel("Nazov jedalne : "));
			nazovJedalne = new JTextField(10);
			nazovJedalnePanel.add(nazovJedalne);
			formularJedalen.add(nazovJedalnePanel);
			
			// Popis 	
			JPanel hodnoteniePanel = new JPanel();
			hodnoteniePanel.add(new JLabel("Hodnotenie jedla (1 - 5) : "));
			hodnotenieJedla = new JTextField(4);
			hodnoteniePanel.add(hodnotenieJedla);
			formularJedalen.add(hodnoteniePanel);

		
	}
	
	
	@Override
	public Map<String, String> vratUdaje() {
		
		Map<String, String> udaje = new LinkedHashMap<>();
		udaje.put("nazov", nazovJedla.getText());
		udaje.put("casJedla", (String)casJedla.getSelectedItem());
		udaje.put("cena", cena.getText());
		udaje.put("nazovJedalne", nazovJedalne.getText());
		udaje.put("hodnotenieJedla", hodnotenieJedla.getText());
		
		return udaje;
	}
	
	
}
