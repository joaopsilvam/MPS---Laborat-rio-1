package business.model.users;

import business.model.IUser;

public class StudentUser extends UserBase{

    private String course;

    public StudentUser(StudentUser studentUser){
        super(studentUser);
        this.course = studentUser.course;
    }

    public StudentUser(){
        super();
    }

    public StudentUser(String login, String pass, String userName, int age, String course) {
        super(login, pass, userName, age);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "Curso: " + course;
    }

    @Override
    public IUser copy() {
        return new StudentUser(this);
    }
}
