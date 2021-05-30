package view;

import business.model.User;
import infra.UserPersistence;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserForm userForm = new UserForm();
		Boolean cond = true;
		while(cond){
			cond = userForm.menu();
		}
		userForm.close();
	}

}
