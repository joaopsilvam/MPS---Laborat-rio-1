package infra;

import exceptions.InfraException;

public interface IReportPersistence {

    void saveFile(String data, String file) throws InfraException;
}
