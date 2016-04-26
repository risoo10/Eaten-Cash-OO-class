package controllers;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class OknoController implements WindowListener{

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}
	
	
	// Pred zatvorenim aplikacie vyskoci okno.
	
	@Override
	public void windowClosing(WindowEvent e) {
		int uloz = JOptionPane.showOptionDialog(null, "Chcete ulozit vsetky zmeny ?", "Uloz zmeny", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);	
		if(uloz == 0){
			UsersController.ulozVsetkyData();
			System.exit(0);
		} else {
			System.exit(0);
		}
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
