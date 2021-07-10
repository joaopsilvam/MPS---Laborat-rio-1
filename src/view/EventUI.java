package view;

import business.control.Facade;
import business.control.command.*;
import business.model.Event;
import business.model.User;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;
import business.model.responses.UserListResponse;
import business.model.responses.UserResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class EventUI implements IForms{

    Facade facade;
	Executor executor;

    public EventUI(Facade facade, Executor executor){
        this.facade = facade;
		this.executor = executor;
	}

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar evento\n[b] Verificar um evento\n[c] Verificar todos os eventos\n" +
                "[d] Apagar evento\n[e] Verificar usuários inscritos em um evento\n[x] Voltar");

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
			listAllUsersOnEvent();
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
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");
		String descricao = JOptionPane.showInputDialog("Informe a descricao do evento");
		Event event = new Event(nome, descricao, new Date());

		CommandWithResult<List<String>> command = new AddEventCommand(this.facade, event);
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
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");

		CommandWithResult<EventResponse> command = new ListOneEventCommand(this.facade, nome);
		executor.performOperation(command);
		EventResponse exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: exceptions.getErrors()) {
			exceptionsText += e+'\n';
		}
		if(!exceptionsText.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			Event event = command.getResult().getEvent();
			JOptionPane.showMessageDialog(null, event.getName()+'\n'+event.getData().toString());
		}
	}

	public void listAllOperation(){

		CommandWithResult<EventListResponse> command = new ListAllEventCommand(this.facade);
		executor.performOperation(command);
		EventListResponse exceptions = command.getResult();

		String nomes = "";
		String exceptionsText = "";

		for(Event event : command.getResult().getEvents()){
			nomes += event.getName() + " - " + event.getData() +'\n';
		}

		for(String exception : command.getResult().getErrors()){
			exceptionsText += exception + '\n';
		}

		JOptionPane.showMessageDialog(null, nomes);

		if(!exceptions.getErrors().isEmpty()){
			JOptionPane.showMessageDialog(null, exceptionsText);
		}
	}

	public void delOperation(){
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");

		CommandWithResult<List<String>> command = new DelEventCommand(this.facade, nome);
		executor.performOperation(command);
		List<String> exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: command.getResult()) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}

	private void listAllUsersOnEvent() {
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");

		String users = "";
		UserListResponse response = facade.listAllUsersOnEvent(nome);
		String errors = "";

		for(String error : response.getErrors()){
			errors += error + '\n';
		}

		if(!errors.isEmpty()){
			JOptionPane.showMessageDialog(null, errors);
			return;
		}

		for (User u : response.getUsers()) {
			users += u.getLogin()+'\n';
		}
		JOptionPane.showMessageDialog(null, users);
    }
}
