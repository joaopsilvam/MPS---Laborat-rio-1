package view;

import business.control.UserControl;
import business.model.responses.UserListResponse;
import business.model.IUser;
import business.model.users.StudentUser;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.util.List;

public class UserForm {

	UserControl controller;

	public UserForm(UserControl controller)
	{
		this.controller = controller;
	}

	public Boolean menu() {
		String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
				"\n[a] Cadastrar usuário\n[b] Verificar um usuário\n[c] Verificar todos os usuários\n" +
				"[d] Deletar usuário\n[x] Sair");

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
		}
		else if(operation.equals("x")) {
			return false;
		}else{
			JOptionPane.showMessageDialog(null, "Informe uma operação válida");
		}
		return true;
	}

	public void addOperation(){
		String option = JOptionPane.showInputDialog("Selecione uma opção:\n[a]Aluno\n[b]Funcionário");
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");
		String password = JOptionPane.showInputDialog("Informe a senha do usuário:");
		String name = JOptionPane.showInputDialog("Informe o nome do usuário:");
		int age = Integer.parseInt(JOptionPane.showInputDialog("Informe a idade do usuário:"));

		IUser user = null;

		switch (option){
			case "a":
				String course = JOptionPane.showInputDialog("Informe o curso do usuário:");
				user = new StudentUser(login, password, name, age, course);
				break;

			case "b":
				String office = JOptionPane.showInputDialog("Informe o trabalho do usuário:");
				user = new StudentUser(login, password, name, age, office);
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");
				return;
		}

		List<String> exceptions = controller.add(user);
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
		for (String e: controller.read(login).getErrors()) {
			exceptions += e+'\n';
		}
		if(!exceptions.isEmpty()) JOptionPane.showMessageDialog(null, exceptions);
		else JOptionPane.showMessageDialog(null, controller.read(login).getUser().getLogin()+
				"\n"+controller.read(login).getUser().getPassword());
	}

	public void listAllOperation(){
		UserListResponse response = controller.readAll();
		String logins = "";
		String exceptionsText = "";

		for(IUser IUser : response.getUsers()){
			logins += IUser.getLogin() + '\n';
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
		for (String e: controller.delete(login)) {
			exceptions += e+'\n';
		}
		JOptionPane.showMessageDialog(null, exceptions);
	}

	public void close(){
		this.controller.saveData();
	}
}
