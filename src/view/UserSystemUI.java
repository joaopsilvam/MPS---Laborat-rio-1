package view;

import business.control.Facade;
import business.model.User;
import exceptions.InfraException;

import javax.swing.*;

public class UserSystemUI implements IForms{

    Facade facade;

    public UserSystemUI(Facade facade)  {
        this.facade = facade;
    }

    public boolean menu(){
        String login = JOptionPane.showInputDialog("Informe o seu login:");
        String pass = JOptionPane.showInputDialog("Informe a sua senha:");

        if(login == null || pass == null){
            return false;
        }

        try {
            login(login, pass);
        }
        catch (InfraException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(1);
        }

        return true;
    }

    private void login(String login, String pass) throws InfraException {
        User user = new User(login, pass);

        facade.login(user);
    }
}
