package view;

import business.control.Facade;
import business.control.command.*;
import business.model.IEvent;
import business.model.events.OnlineEvent;
import business.model.events.PresentialEvent;
import business.model.User;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;
import business.model.responses.UserListResponse;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
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
                "\n[a] Publicar evento presencial\n[b] Publicar evento online\n[c] Verificar um evento\n[d] Verificar todos os eventos\n" +
                "[e] Apagar evento\n[f] Verificar usuários inscritos em um evento\n[x] Voltar");

		if(operation == null){
			operation = "x";
		}

		switch (operation) {
		case "a":
			addPresentialOperation();
			break;
		case "b":
			addOnlineOperation();
			break;
		case "c":
			listOneOperation();
			break;
		case "d":
			listAllOperation();
			break;
		case "e":
			delOperation();
			break;
		case "f":
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

	public void addOnlineOperation(){
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");
		String descricao = JOptionPane.showInputDialog("Informe a descricao do evento");
		String link = JOptionPane.showInputDialog("Informe o link do evento");
		IEvent event = new OnlineEvent(nome, descricao, new Date(), new HashMap<>(), link);

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

	public void addPresentialOperation(){
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");
		String descricao = JOptionPane.showInputDialog("Informe a descricao do evento");
		String local = JOptionPane.showInputDialog("Informe o local do evento");
		IEvent event = new PresentialEvent(nome, descricao, new Date(), new HashMap<>(), local);

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
			IEvent event = command.getResult().getEvent();
			JOptionPane.showMessageDialog(null, event.getDetails());
		}
	}

	public void listAllOperation(){

		CommandWithResult<EventListResponse> command = new ListAllEventCommand(this.facade);
		executor.performOperation(command);
		EventListResponse exceptions = command.getResult();

		String nomes = "";
		String exceptionsText = "";

		for(IEvent event : command.getResult().getEvents()){
			nomes += event.getDetails() + "\n\n";
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
