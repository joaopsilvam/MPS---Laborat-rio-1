package business.model.users;

import business.model.IUser;

public class EmployeeUser extends UserBase{

    private String office;

    public EmployeeUser(){
        super();
        office = "";
    }

    public EmployeeUser(EmployeeUser user){
        super(user);
        office = user.office;
    }

    public EmployeeUser(String login, String userName, String password, int age, String office){
        super(login, userName, password, age);
        this.office = office;
    }

    @Override
    public IUser copy() {
        return new EmployeeUser(this);
    }
}
