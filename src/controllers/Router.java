package controllers;

import java.awt.event.*;

import javax.swing.*;

public class Router {
	
	private static ActionListener router = new ActionListener(){

		// ROuter presmeruje po stlaceni tlacidla na skutocny Controller
		public void actionPerformed(ActionEvent e) {
			String actionId = e.getActionCommand();
			
			switch(actionId){
				
					
				case "Master":
					break;
					
				// Prihlasenie Simple User 
				case "Prihlasenie Obycajny pouzivatel":
					SimpleUserController.getPrihlasenieSimpleUsers();
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
					break;
					
					// Prihlasenie RODIC	
				case "Prihlasenie Rodic":
					RodicController.getPrihlasenieRodic();
					break;
					
				case "RodicGetDomov":
					userId = ((JButton)e.getSource()).getText();
					RodicController.getDomov(userId);
					break;
					
				case "NovyRodicGetDomov":
					RodicController.getNovyRodicDomov();
					break;
			}
			
		}
		
	};
	
	public static ActionListener getRouter(){
		return router;
	}
	

}
