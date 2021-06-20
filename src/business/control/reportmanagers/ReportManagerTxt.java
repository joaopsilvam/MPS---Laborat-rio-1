package business.control.reportmanagers;

import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

public class ReportManagerTxt extends ReportManagerBase{

    @Override
    protected String generateContent(List<UserStatistic> statistics){
        return "";
    }
}
