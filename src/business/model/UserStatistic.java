package business.model;

import java.io.Serializable;
import java.util.Date;

public class UserStatistic implements Serializable {

    private Date loginDate;
    private User user;

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
