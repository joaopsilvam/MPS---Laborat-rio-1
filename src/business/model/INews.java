package business.model;

import java.util.Date;

public interface INews {

    String getTitle();
    void setTitle(String titulo);
    String getDescription();
    void setDescription(String descricao);
    Date getDate();
    void setDate(Date data);
    String getDetails();
    INews copy();
}
