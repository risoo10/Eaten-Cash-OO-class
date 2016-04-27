package controllers;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import views.UserDomov;

public class RouterMouse {
	
	private static MouseListener routerMouse = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
			
			// ked bola stlacena tabulka 
			if(e.getSource() instanceof JTable){
				JTable tabulka = (JTable)e.getSource();
				Point clicked = e.getPoint();
				int column = tabulka.columnAtPoint(clicked);
				int row = tabulka.rowAtPoint(clicked);
				
				// Ak je stlacene tlacidlo v stvrtom stlpci tak nezobrazi detaily.
				if(column != UserDomov.COLUMN_BUTTON)
					UserController.zobrazDetailJedla(row);
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};

	public static MouseListener getRouterMouse(){
		return routerMouse;
	}
}	