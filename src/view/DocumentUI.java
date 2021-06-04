package view;

import business.control.Facade;
import business.model.Document;
import business.model.Event;
import business.model.responses.DocumentListReponse;
import business.model.responses.DocumentResponse;
import business.model.responses.EventListResponse;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class DocumentUI implements IForms{

    Facade facade;

    public DocumentUI(Facade facade){
        this.facade = facade;
    }

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar documento\n[b] Verificar um documento\n[c] Verificar todos os documentos\n" +
                "[d] Apagar documento\n[x] Voltar");

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

        List<String> exceptions = facade.addDocument(document);
        String exceptionsText = "";

        for (String e : exceptions) {
            exceptionsText += e+'\n';
        }

        if(!exceptions.isEmpty())
            JOptionPane.showMessageDialog(null, exceptionsText);
    }

    public void listOneOperation(){
        String name = JOptionPane.showInputDialog("Informe o nome do documento");

        String exceptions = "";
        DocumentResponse response = facade.readDocument(name);
        Document document = response.getDocument();

        for (String e: response.getErrors()) {
            exceptions += e+'\n';
        }
        if(!exceptions.isEmpty()){
            JOptionPane.showMessageDialog(null, exceptions);
        }
        else{
            JOptionPane.showMessageDialog(null, '<' + document.getName()+">\n"+document.getData() + '\n');
        }
    }

    public void listAllOperation(){
        DocumentListReponse response = facade.readAllDocuments();
        String names = "";
        String exceptionsText = "";

        for(Document document : response.getDocuments()){
            names += '<' + document.getName() + ">\n" + document.getData() +'\n';
        }

        for(String exception : response.getErrors()){
            exceptionsText += exception + '\n';
        }

        JOptionPane.showMessageDialog(null, names);

        if(!response.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(null, exceptionsText);
        }
    }

    public void delOperation(){
        String name = JOptionPane.showInputDialog("Informe o nome do documento");

        String exceptions = "";
        for (String e: facade.deleteDocument(name)) {
            exceptions += e+'\n';
        }

        if(!exceptions.isEmpty())
            JOptionPane.showMessageDialog(null, exceptions);
    }
}
