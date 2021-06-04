package view;

import business.control.Facade;
import business.model.responses.UserListResponse;
import business.model.User;

import javax.swing.*;
import java.util.List;

public class UserUI implements IForms{

	Facade facade;

	public UserUI(Facade facade){
		this.facade = facade;
	}

	public boolean menu() {
		String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
				"\n[a] Cadastrar usuário\n[b] Verificar um usuário\n[c] Verificar todos os usuários\n" +
				"[d] Deletar usuário\n[e] Cadastrar usuário em um evento\n[x] Voltar");

		if(operation == null){
			operation = "x";
		}

		switch (operation) {
			case "a":
				addOperation();
				break;
			case "b":
				listOneOperation();
				break;
			case "c":
				listAllOperation();
				break;
			case "d":
				delOperation();
				break;
			case "e":
				addUserIntoEvento();
				break;
			case "x":
				return false;
			default:
				JOptionPane.showMessageDialog(null, "Informe uma operação válida");
				break;
		}
		return true;
	}

	public void addOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");
		String password = JOptionPane.showInputDialog("Informe a senha do usuário:");
		User user = new User(login, password);

		List<String> exceptions = facade.addUser(user);
		String exceptionsText = "";

		for (String e : exceptions) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}

	public void listOneOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		String exceptions = "";
		for (String e: facade.readUser(login).getErrors()) {
			exceptions += e+'\n';
		}
		if(!exceptions.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			User user = facade.readUser(login).getUser();
			JOptionPane.showMessageDialog(null, user.getLogin()+'\n'+user.getPassword());
		}
	}

	public void listAllOperation(){
		UserListResponse response = facade.readAllUsers();
		String logins = "";
		String exceptionsText = "";

		for(User user : response.getUsers()){
			logins += user.getLogin() + '\n';
		}

		for(String exception : response.getErrors()){
			exceptionsText += exception + '\n';
		}

		JOptionPane.showMessageDialog(null, logins);

		if(!response.getErrors().isEmpty()){
			JOptionPane.showMessageDialog(null, exceptionsText);
		}
	}

	public void delOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		String exceptions = "";
		for (String e: facade.deleteUser(login)) {
			exceptions += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptions);
	}

	public void addUserIntoEvento() {
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");
		String eventName = JOptionPane.showInputDialog("Informe o nome do evento:");
		String exceptions = "";
		for (String e: facade.addUserIntoEvent(login, eventName)) {
			exceptions += e+'\n';
		}
		if(!exceptions.equals("")) JOptionPane.showMessageDialog(null, exceptions);
		else JOptionPane.showMessageDialog(null, "O usuário foi cadastrado no evento com sucesso!");
	}

}