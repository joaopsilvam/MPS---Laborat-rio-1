package view;

import business.control.Facade;
import business.control.command.*;
import business.model.Document;
import business.model.Event;
import business.model.responses.DocumentListReponse;
import business.model.responses.DocumentResponse;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class DocumentUI implements IForms{

    Facade facade;
    Executor executor;

    public DocumentUI(Facade facade, Executor executor){
        this.facade = facade;
        this.executor = executor;
    }

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar documento\n[b] Verificar um documento\n[c] Verificar todos os documentos\n" +
                "[d] Apagar documento\n[x] Voltar");

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
        String name = JOptionPane.showInputDialog("Informe o nome do documento");
        String content = JOptionPane.showInputDialog("Informe o conteúdo do documento");
        Document document = new Document(content, name, new Date());

        CommandWithResult<List<String>> command = new AddDocumentCommand(this.facade, document);
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
        String name = JOptionPane.showInputDialog("Informe o nome do documento");

        CommandWithResult<DocumentResponse> command = new ListOneDocumentCommand(this.facade, name);
        executor.performOperation(command);
        DocumentResponse exceptions = command.getResult();

        String exceptionsText = "";
        for (String e: exceptions.getErrors()) {
            exceptionsText += e+'\n';
        }
        if(!exceptionsText.isEmpty()){
            JOptionPane.showMessageDialog(null, exceptions);
        }
        else{
            Document document = command.getResult().getDocument();
            JOptionPane.showMessageDialog(null, '<' + document.getName()+">\n"+document.getData() + '\n');
        }
    }

    public void listAllOperation(){
        CommandWithResult<DocumentListReponse> command = new ListAllDocumentCommand(this.facade);
        executor.performOperation(command);
        DocumentListReponse exceptions = command.getResult();

        String nomes = "";
        String exceptionsText = "";

        for(Document document : command.getResult().getDocuments()){
            nomes += document.getName() + " - " + document.getData() +'\n';
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
        String name = JOptionPane.showInputDialog("Informe o nome do documento");

        CommandWithResult<List<String>> command = new DelDocumentCommand(this.facade, name);
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
