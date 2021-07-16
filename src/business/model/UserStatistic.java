package business.model;

import java.io.Serializable;
import java.util.Date;

public class UserStatistic implements Serializable {

    private Date loginDate;
    private IUser IUser;

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public IUser getUser() {
        return IUser;
    }

    public void setUser(IUser IUser) {
        this.IUser = IUser;
    }
}
