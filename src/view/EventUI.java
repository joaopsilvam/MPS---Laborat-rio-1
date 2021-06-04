package view;

import business.control.Facade;
import business.model.Event;
import business.model.responses.EventListResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class EventUI implements IForms{

    Facade facade;

    public EventUI(Facade facade){
        this.facade = facade;
    }

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar evento\n[b] Verificar um evento\n[c] Verificar todos os eventos\n" +
                "[d] Apagar evento\n[x] Voltar");

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

		List<String> exceptions = facade.addEvent(event);
		String exceptionsText = "";

		for (String e : exceptions) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}

	public void listOneOperation(){
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");

		String exceptions = "";
		for (String e: facade.readEvent(nome).getErrors()) {
			exceptions += e+'\n';
		}
		if(!exceptions.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			Event event = facade.readEvent(nome).getEvent();
			JOptionPane.showMessageDialog(null, event.getName()+'\n'+event.getData().toString());
		}
	}

	public void listAllOperation(){
		EventListResponse response = facade.readAllEvents();
		String nomes = "";
		String exceptionsText = "";

		for(Event event : response.getEvents()){
			nomes += event.getName() + '\t' + event.getData() +'\n';
		}

		for(String exception : response.getErrors()){
			exceptionsText += exception + '\n';
		}

		JOptionPane.showMessageDialog(null, nomes);

		if(!response.getErrors().isEmpty()){
			JOptionPane.showMessageDialog(null, exceptionsText);
		}
	}

	public void delOperation(){
		String nome = JOptionPane.showInputDialog("Informe o nome do evento");

		String exceptions = "";
		for (String e: facade.deleteEvent(nome)) {
			exceptions += e+'\n';
		}
		JOptionPane.showMessageDialog(null, exceptions);
	}
}
