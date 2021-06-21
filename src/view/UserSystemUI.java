package view;

import business.control.Facade;
import business.model.User;

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

        login(login, pass);

        return true;
    }

    private void login(String login, String pass) {
        User user = new User(login, pass);

        facade.login(user);
    }
}
