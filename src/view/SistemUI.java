package view;

import business.control.Facade;
import business.control.command.Manager;
import exceptions.InfraException;

import javax.swing.*;

public class SistemUI implements IForms{

    Facade facade;
    Manager manager = new Manager();

    public SistemUI() throws InfraException {
        this.facade = Facade.getInstance();
        this.manager = new Manager();
    }

    public boolean menu(){
        String operation = JOptionPane.showInputDialog("Como você deseja entrar no sistema?" +
                "\n[a] Usuário\n[b] Gerente\n[x] Sair");

        if(operation == null){
            operation = "x";
        }

        switch (operation) {
            case "a":
                sectionUser();
                break;
            case "b":
                sectionManager();
                break;
            case "x":
                return false;
            default:
                JOptionPane.showMessageDialog(null, "Informe uma operação válida");
                break;
        }
        return true;
    }

    private void sectionUser() {
        new UserSystemUI(this.facade).menu();
    }
    private void sectionManager() { new ManagerSystemUI(this.facade, this.manager).menu(); }
}
