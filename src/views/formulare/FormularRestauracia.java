package views.formulare;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;


public class FormularRestauracia extends JPanel implements Formular {
	
	private JTextField nazovJedla = null;
	private String[] casy = {"Ranajky", "Obed", "Vecera"};
	private JComboBox<String> casJedla;
	private JTextField cena;
	private JTextField nazovRestauracie;
	private JTextField hodnotenieRestauracie;
	private JTextField tip;
	
	
	public FormularRestauracia(JPanel dodatocnyFormular, JTextField nazovJedla){
		
		this.nazovJedla = nazovJedla;
		
		JPanel formularRestauracia = dodatocnyFormular;
		formularRestauracia.setPreferredSize(new Dimension(500, 200));
		
			// label Cas
			formularRestauracia.add(new JLabel("Vyber cas : "));
			
			// ComboBox Cas
			casJedla = new JComboBox<String>(casy);
			formularRestauracia.add(casJedla);
			
			// label Cena
			formularRestauracia.add(new JLabel("    Cena : "));
			
			// textfield Cena
			cena = new JTextField(5);
			formularRestauracia.add(cena);
			
			// Euro label
			formularRestauracia.add(new JLabel("€"));
			
			
			// Panel TextArea Nazov Restauracie
			JPanel nazovRestauraciePanel = new JPanel();
			nazovRestauraciePanel.add(new JLabel("Nazov Restauracie : "));
			nazovRestauracie = new JTextField(15);
			nazovRestauraciePanel.add(nazovRestauracie);
			formularRestauracia.add(nazovRestauraciePanel);
			
			// Hodnotenie Restauracie
			JPanel hodnoteniePanel = new JPanel();
			hodnoteniePanel.add(new JLabel("Hodnotenie restauracie (1 - 5) : "));
			hodnotenieRestauracie = new JTextField(4);
			hodnoteniePanel.add(hodnotenieRestauracie);
			formularRestauracia.add(hodnoteniePanel);
			
			// Tip na Dalsie Jedlo z Restauracie
			JPanel tipPanel = new JPanel();
			tipPanel.add(new JLabel("Tip na dalsie jedlo : "));
			tip = new JTextField(15);
			tipPanel.add(tip);
			formularRestauracia.add(tipPanel);
		
	}
	
	
	
	@Override
	public Map<String, String> vratUdaje() {

		Map<String, String> udaje = new LinkedHashMap<>();
		udaje.put("nazov", nazovJedla.getText());
		udaje.put("casJedla", (String)casJedla.getSelectedItem());
		udaje.put("cena", cena.getText());
		udaje.put("nazovRestauracie", nazovRestauracie.getText());
		udaje.put("hodnotenieRestauracie", hodnotenieRestauracie.getText());
		udaje.put("tip", tip.getText());
		
		return udaje;
	}
}
