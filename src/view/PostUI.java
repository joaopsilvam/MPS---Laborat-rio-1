package view;

import business.control.Facade;
import business.control.command.*;
import business.model.Post;
import business.model.responses.PostListResponse;
import business.model.responses.PostResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class PostUI implements IForms{

    Facade facade;
	Executor executor;

    public PostUI(Facade facade, Executor executor){
        this.facade = facade;
		this.executor = executor;
	}

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar noticia\n[b] Verificar uma noticia\n[c] Verificar todas as noticias\n" +
                "[d] Apagar noticia\n[x] Voltar");

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
		case "x":
			return false;
		default:
			JOptionPane.showMessageDialog(null, "Informe uma operação válida");
			break;
		}
        return true;
    }

	public void addOperation(){
		String nome = JOptionPane.showInputDialog("Informe o titulo da noticia");
		String descricao = JOptionPane.showInputDialog("Informe a descricao da noticia");
		Post event = new Post(nome, descricao, new Date());

		CommandWithResult<List<String>> command = new AddPostCommand(this.facade, event);
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
		String nome = JOptionPane.showInputDialog("Informe o titulo da noticia");

		CommandWithResult<PostResponse> command = new ListOnePostCommand(this.facade, nome);
		executor.performOperation(command);
		PostResponse exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: exceptions.getErrors()) {
			exceptionsText += e+'\n';
		}
		if(!exceptionsText.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			Post event = command.getResult().getPost();
			JOptionPane.showMessageDialog(null, event.getTitulo()+'\n'+event.getData().toString());
		}
	}

	public void listAllOperation(){

		CommandWithResult<PostListResponse> command = new ListAllPostCommand(this.facade);
		executor.performOperation(command);
		PostListResponse exceptions = command.getResult();

		String nomes = "";
		String exceptionsText = "";

		for(Post post : command.getResult().getPosts()){
			nomes += post.getTitulo() + " - " + post.getData() +'\n';
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
		String nome = JOptionPane.showInputDialog("Informe o titulo da noticia");

		CommandWithResult<List<String>> command = new DelPostCommand(this.facade, nome);
		executor.performOperation(command);
		List<String> exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: command.getResult()) {
			exceptionsText += e+'\n';
		}

		if(!exceptions.isEmpty())
			JOptionPane.showMessageDialog(null, exceptionsText);
	}
}
