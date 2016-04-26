package controllers;

import java.awt.event.*;

import javax.swing.*;

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
					
				// Prihlasenie Simple User 
				case "Obycajny pouzivatel":
					SimpleUserController.getSimpleUsers();
					break;
					
				case "SimpleUserGetDomov":
					String userId = ((JButton)e.getSource()).getText();
					SimpleUserController.getDomov(userId);
					break;
					
				case "NovySimpleUserGetDomov":
					SimpleUserController.getNovyUserDomov();
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
