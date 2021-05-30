package view;

import business.control.UserControl;
import business.model.ListUserResponse;
import business.model.User;
import exceptions.UserException;

import javax.swing.*;
import java.util.List;

public class UserForm {

	UserControl controller = new UserControl();

	public void menu() {
		String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
				"\n[a] Cadastrar usuário\n[b] Verificar um usuário\n[c] Verificar todos os usuários\n" +
				"[d] Deletar usuário");

		if(operation.equals("a")) {
			addOperation();
		}
		else if(operation.equals("b")) {
			listOneOperation();
		}
		else if(operation.equals("c")) {
			listAllOperation();
		}
		else if(operation.equals("d")) {
			delOperation();
		}else{
			JOptionPane.showMessageDialog(null, "Informe uma operação válida");
		}
	}

	public void addOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");
		String password = JOptionPane.showInputDialog("Informe a senha do usuário:");
		User user = new User(login, password);

		String exceptions = "";
		for (UserException e: controller.add(user)) {
			exceptions += e.getMessage()+"\n";
		}
		JOptionPane.showMessageDialog(null, exceptions);
	}

	public void listOneOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		String exceptions = "";
		for (UserException e: controller.read(login).getExceptions()) {
			exceptions += e.getMessage()+"\n";
		}
		JOptionPane.showMessageDialog(null, exceptions);
	}

	public void listAllOperation(){
	}
	public void delOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		String exceptions = "";
		for (UserException e: controller.delete(login)) {
			exceptions += e.getMessage()+"\n";
		}
		JOptionPane.showMessageDialog(null, exceptions);
	}
}
