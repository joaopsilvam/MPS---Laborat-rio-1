package view;

import business.control.Facade;

import javax.swing.*;

public class EventUI implements IForms{

    Facade facade;

    public EventUI(Facade facade){
        this.facade = facade;
    }

    public boolean menu() {
        String operation = JOptionPane.showInputDialog("Que operação você deseja fazer no sistema?" +
                "\n[a] Publicar evento\n[b] Verificar um evento\n[c] Verificar todos os eventos\n" +
                "[d] Apagar evento\n[x] Voltar");

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
