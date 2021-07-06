package view;

import business.control.Facade;
import business.control.command.*;
import business.model.responses.UserListResponse;
import business.model.User;
import business.model.responses.UserResponse;

import javax.swing.*;
import java.util.List;

public class UserUI implements IForms{

	Facade facade;
	Executor executor;

	private static Command lastCommand;

	public UserUI(Facade facade, Executor executor){
		this.facade = facade;
		this.executor = executor;
	}

	public boolean menu() {
		String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
				"\n[a] Cadastrar usuário\n[b] Verificar um usuário\n[c] Verificar todos os usuários\n" +
				"[d] Deletar usuário\n[e] Cadastrar usuário em um evento\n[f] Descadastrar último usuário\n[x] Voltar");

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
			case "f":
				if(lastCommand != null) lastCommand.undo();
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

		CommandWithResult<List<String>> command = new AddUserCommand(this.facade, user);
		executor.performOperation(command);
		List<String> exceptions = command.getResult();

		String exceptionsText = "";

		for (String e : exceptions) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}

	public void listOneOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		CommandWithResult<UserResponse> command = new ListOneUserCommand(this.facade, login);
		executor.performOperation(command);
		UserResponse exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: exceptions.getErrors()) {
			exceptionsText += e+'\n';
		}
		if(!exceptionsText.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			User user = command.getResult().getUser();
			JOptionPane.showMessageDialog(null, user.getLogin()+'\n'+user.getPassword());
		}
	}

	public void listAllOperation(){
		CommandWithResult<UserListResponse> command = new ListAllUserCommand(this.facade);
		executor.performOperation(command);
		UserListResponse exceptions = command.getResult();

		String logins = "";
		String exceptionsText = "";

		for(User user : command.getResult().getUsers()){
			logins += user.getLogin() + '\n';
		}

		for(String exception : command.getResult().getErrors()){
			exceptionsText += exception + '\n';
		}

		JOptionPane.showMessageDialog(null, logins);

		if(!exceptions.getErrors().isEmpty()){
			JOptionPane.showMessageDialog(null, exceptionsText);
		}
	}

	public void delOperation(){
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");

		CommandWithResult<List<String>> command = new DelUserCommand(this.facade, login);
		executor.performOperation(command);
		List<String> exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: command.getResult()) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}

	public void addUserIntoEvento() {
		String login = JOptionPane.showInputDialog("Informe o login do usuário:");
		String eventName = JOptionPane.showInputDialog("Informe o nome do evento:");

		AddUserIntoEventCommand command = new AddUserIntoEventCommand(this.facade, login, eventName);
		executor.performOperation(command);
		this.lastCommand = command;
		List<String> exceptions = command.getResult();
		String exceptionsText = "";
		for (String e: command.getResult()) {
			exceptionsText += e+'\n';
		}
		if(!exceptionsText.equals("")) JOptionPane.showMessageDialog(null, exceptionsText);
		else JOptionPane.showMessageDialog(null, "O usuário foi cadastrado no evento com sucesso!");
	}

}