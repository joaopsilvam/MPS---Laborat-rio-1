package view;

import business.control.UserControl;
import exceptions.InfraException;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		IForms sistemUI = null;
		try {
			sistemUI = new SistemUI();
		} catch (InfraException e) {
			e.printStackTrace();
		}
		Boolean cond = true;

//		try {
//			sistemUI = new SistemUI();
//		}
//		catch (InfraException e){
//			JOptionPane.showMessageDialog(null, e.getMessage());
//			return;
//		}

		while(cond){
			cond = sistemUI.menu();
		}
//		sistemUI.close();
	}

}
