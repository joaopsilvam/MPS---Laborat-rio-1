package view;

import business.control.Facade;
import business.model.IUser;
import business.model.responses.UserResponse;
import business.model.users.StudentUser;
import util.InfraException;

import javax.swing.*;
import java.util.List;

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

        UserResponse userResponse = facade.readUser(login);
        IUser user = userResponse.getUser();
        List<String> errors = userResponse.getErrors();

        if(errors.size() > 0){
            String errorText = "";

            for(String error : errors){
                errorText += error + '\n';
            }

            JOptionPane.showMessageDialog(null, errorText);
            return;
        }

        if(!user.getPassword().equals(pass)){
            JOptionPane.showMessageDialog(null, "Senha inválida!");
            return;
        }

        facade.login(user);
    }
}
