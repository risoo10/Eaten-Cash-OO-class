package controllers;

import java.awt.event.*;

import javax.swing.JComboBox;

public class Router {
	
	private static ActionListener router = new ActionListener(){

		// ROuter presmeruje po stlaceni tlacidla na skutocny Controller
		public void actionPerformed(ActionEvent e) {
			String actionId = e.getActionCommand();
			
			switch(actionId){
				case "Rodic":				
					break;
					
				case "Master":
					break;
					
				// User 
				case "User":
					UserController.getDomov();
					break;
					
				case "UserSpat":
					UserController.getDomovBack();
					break;
					
				case "Spat":
					UserController.getPrihlasenie();
					break;
					
				case "NoveJedlo":
					UserController.getPridajNoveJedlo();
					break;
					
				case "UlozJedlo":
					UserController.ulozNoveJedlo();
					break;
					
				case "MiestoComboBox":
					JComboBox combo = (JComboBox)e.getSource();
					String activeValue = (String)combo.getSelectedItem();
					NoveJedloController.comboBoxPanel(activeValue);
				
			}
			
		}
		
	};
	
	public static ActionListener getRouter(){
		return router;
	}
	

}
