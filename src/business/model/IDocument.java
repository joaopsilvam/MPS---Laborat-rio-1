package business.model;

import java.util.Date;

public interface IDocument {

    String getName();
    void setName(String name);
    String getData();
    void setData(String data);
    Date getPostDate();
    void setPostDate(Date postDate);
    String getDetails();
}
