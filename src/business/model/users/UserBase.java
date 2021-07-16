package business.model.users;

import business.model.IUser;

import java.io.Serializable;
import java.util.Random;

public abstract class UserBase implements Serializable, IUser {

    private static final Random RANDOM = new Random();

    private String login;
    private String userName;
    private String pass;
    private int age;
    private int registration;

    public UserBase(UserBase userBase){
        login = userBase.login;
        userName = userBase.userName;
        pass = userBase.pass;
        age = userBase.age;
        registration = userBase.registration;
    }

    public UserBase(){
        this("", "", "", 1);
    }

    public UserBase(String login, String pass, String userName, int age){
        int rand = RANDOM.nextInt();
        rand = Math.abs(rand);

        this.login = login;
        this.pass = pass;
        this.userName = userName;
        this.age = age;
        this.registration = rand;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password){
        this.pass = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    @Override
    public String getDetails() {
        return "Login: " + login + '\n' +
                "Nome: " + userName + '\n' +
                "Idade: " + age + '\n' +
                "Matrícula: " + registration + '\n';
    }
}
