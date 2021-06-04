package view;

import business.control.Facade;
import exceptions.InfraException;

import javax.swing.*;

public class SistemUI implements IForms{

    Facade facade;

    public SistemUI() throws InfraException {
        this.facade = Facade.getInstance();
    }

    public boolean menu(){
        String operation = JOptionPane.showInputDialog("Com o quê você deseja trabalhar?" +
                "\n[a] Usuários\n[b] Eventos\n[c] Documentos\n[x] Sair");

        if(operation.equals("a")) {
            sectionUsers();
        }
        else if(operation.equals("b")) {
            sectionEvents();
        }
        else if(operation.equals("c")) {
            sectionDocuments();
        }
        else if(operation.equals("x")) {
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Informe uma operação válida");
        }
        return true;
    }

    private void sectionUsers() {
        new UserUI(this.facade).menu();
    }
    private void sectionEvents() {
        new EventUI(this.facade).menu();
    }
    private void sectionDocuments() {
        new DocumentUI(this.facade).menu();
    }
}
