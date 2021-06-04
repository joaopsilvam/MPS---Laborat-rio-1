package view;

import business.control.Facade;
import business.model.Document;

import javax.swing.*;

public class DocumentUI implements IForms{

    Facade facade;

    public DocumentUI(Facade facade){
        this.facade = facade;
    }

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar documento\n[b] Verificar um documento\n[c] Verificar todos os documentos\n" +
                "[d] Deletar documento\n[x] Voltar");

        if(operation.equals("a")) {
        }
        else if(operation.equals("b")) {
        }
        else if(operation.equals("c")) {
        }
        else if(operation.equals("d")) {
        }
        else if(operation.equals("x")) {
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Informe uma operação válida");
        }
        return true;
    }
}
