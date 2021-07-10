package view;

import business.control.Facade;
import util.InfraException;

import javax.swing.*;

public class ReportUI {
    Facade facade;

    public ReportUI(Facade facade) {
        this.facade = facade;
    }

    public boolean menu() throws InfraException {
        String operation = JOptionPane.showInputDialog("Em que formato você deseja baixar o relatório?" +
                "\n[a] TXT\n[b] HTML\n[c] PDF\n[x] Voltar");

        if(operation == null){
            operation = "x";
        }

        switch (operation) {
            case "a":
                downloadTXT();
                break;
            case "b":
                downloadHTML();
                break;
            case "c":
                downloadPDF();
                break;
            case "x":
                return false;
            default:
                JOptionPane.showMessageDialog(null, "Informe uma operação válida");
                break;
        }
        return true;
    }

    private void downloadTXT() throws InfraException {
        facade.saveReport("txt");
    }

    private void downloadHTML() throws InfraException {
        facade.saveReport("html");
    }

    private void downloadPDF() throws InfraException {
        facade.saveReport("pdf");
    }
}
