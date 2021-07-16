package view;

import business.control.Facade;
import business.control.command.*;
import business.model.*;
import business.model.documents.EditalDocument;
import business.model.documents.ResourceFormDocument;
import business.model.responses.DocumentListReponse;
import business.model.responses.DocumentResponse;

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
        IDocument document = null;

        while(document == null) {
            String documentType = JOptionPane.showInputDialog("Qual tipo de documento você deseja publicar?" +
                    "\n[a] Documento de Edital\n[b] Formulário de Recurso");
            switch (documentType) {
                case "a":
                    document = new EditalDocument(content, name, new Date());
                    break;
                case "b":
                    document = new ResourceFormDocument(content, name, new Date());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "O tipo de documento informado não existe no sistema.");
            }
        }
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
            IDocument document = command.getResult().getDocument();
            JOptionPane.showMessageDialog(null, '<' + document.getDetails() + '\n');
        }
    }

    public void listAllOperation(){
        CommandWithResult<DocumentListReponse> command = new ListAllDocumentCommand(this.facade);
        executor.performOperation(command);
        DocumentListReponse exceptions = command.getResult();

        String nomes = "";
        String exceptionsText = "";

        for(IDocument document : command.getResult().getDocuments()){
            nomes += document.getDetails()+"\n";
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
        String name = JOptionPane.showInputDialog("Informe o nome do documento: ");
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
