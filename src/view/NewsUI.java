package view;

import business.control.Facade;
import business.control.command.*;
import business.model.INews;
import business.model.news.EditalNews;
import business.model.news.SelectiveProcessNews;
import business.model.responses.NewsListResponse;
import business.model.responses.NewsResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class NewsUI implements IForms{

    Facade facade;
	Executor executor;

    public NewsUI(Facade facade, Executor executor){
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

		String title = JOptionPane.showInputDialog("Informe o titulo da noticia");
		String description = JOptionPane.showInputDialog("Informe a descricao da noticia");
		INews news = null;

		while(news == null) {
			String newsType = JOptionPane.showInputDialog("Sobre o quê você deseja publicar?" +
					"\n[a] Movimentação de editais\n[b] Andamento de processos seletivos");
			switch (newsType) {
				case "a":
					String linkEdital = JOptionPane.showInputDialog("Qual o link do site em que está publicado o edital?");
					news = new EditalNews(title, description, new Date(), linkEdital);
					break;
				case "b":
					news = new SelectiveProcessNews(title, description, new Date());
					break;
				default:
					JOptionPane.showMessageDialog(null, "Informe um tipo de publicação válida.");
			}
		}

		CommandWithResult<List<String>> command = new AddNewsCommand(this.facade, news);
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
		String title = JOptionPane.showInputDialog("Informe o titulo da notícia");

		CommandWithResult<NewsResponse> command = new ListOneNewsCommand(this.facade, title);
		executor.performOperation(command);
		NewsResponse exceptions = command.getResult();

		String exceptionsText = "";
		for (String e: exceptions.getErrors()) {
			exceptionsText += e+'\n';
		}
		if(!exceptionsText.isEmpty()){
			JOptionPane.showMessageDialog(null, exceptions);
		}
		else{
			INews event = command.getResult().getPost();
			JOptionPane.showMessageDialog(null, event.getTitle()+'\n'+event.getDate().toString());
		}
	}

	public void listAllOperation(){

		CommandWithResult<NewsListResponse> command = new ListAllNewsCommand(this.facade);
		executor.performOperation(command);
		NewsListResponse exceptions = command.getResult();

		String nomes = "";
		String exceptionsText = "";

		for(INews news : command.getResult().getPosts()){
			nomes += news.getDetails() +'\n';
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

		CommandWithResult<List<String>> command = new DelNewsCommand(this.facade, nome);
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
