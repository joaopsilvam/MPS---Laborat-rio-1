package view;

import business.control.UserControl;
import business.model.User;

import javax.swing.*;

public class UserForm {

	UserControl controller = new UserControl();

	public void menu() {
		String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
				"\n[a] Cadastrar usuário\n[b] Verificar um usuário\n[c] Verificar todos os usuários\n" +
				"[d] Deletar usuário");

		if(operation.equals("a")) {
			String login = JOptionPane.showInputDialog("Informe o login do usuário:");
			String password = JOptionPane.showInputDialog("Informe a senha do usuário:");
			User user = new User(login, password);
			controller.add(user);
			JOptionPane.showMessageDialog(null, "Usuário cadastrado");
		}
		else if(operation.equals("b")) {
			controller.listAll();
			JOptionPane.showMessageDialog(null, "Verificar um usuário");
		}
		else if(operation.equals("c")) {
			String login = JOptionPane.showInputDialog("Informe o login do usuário:");
			controller.read(login);
			JOptionPane.showMessageDialog(null, "Verificar todos os usuários");
		}
		else if(operation.equals("d")) {
			String login = JOptionPane.showInputDialog("Informe o login do usuário:");
			controller.delete(login);
			JOptionPane.showMessageDialog(null, "Usuário deletado");
		}else{
			JOptionPane.showMessageDialog(null, "Informe uma operação válida");
		}
	}
}
