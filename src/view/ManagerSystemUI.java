package view;

import business.control.Facade;
import exceptions.InfraException;

import javax.swing.*;

public class ManagerSystemUI implements IForms{

    Facade facade;

    public ManagerSystemUI(Facade facade)  {
        this.facade = facade;
    }

    public boolean menu(){
        String operation = JOptionPane.showInputDialog("Com o quê você deseja trabalhar?" +
                "\n[a] Usuários\n[b] Eventos\n[c] Documentos\n[d] Relatórios\n[x] Sair");

        if(operation == null){
            operation = "x";
        }

        switch (operation) {
            case "a":
                sectionUsers();
                break;
            case "b":
                sectionEvents();
                break;
            case "c":
                sectionDocuments();
                break;
            case "d":
                sectionReports();
                break;
            case "x":
                return false;
            default:
                JOptionPane.showMessageDialog(null, "Informe uma operação válida");
                break;
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
    private void sectionReports() {
        try {
            new ReportUI(this.facade).menu();
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
