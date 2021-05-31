package view;

import business.control.UserControl;
import business.model.User;
import exceptions.InfraException;
import infra.UserPersistence;

import javax.swing.*;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserForm userForm = null;
		Boolean cond = true;

		try {
			userForm = new UserForm(new UserControl());
		}
		catch (InfraException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}

		while(cond){
			cond = userForm.menu();
		}
		userForm.close();
	}

}
